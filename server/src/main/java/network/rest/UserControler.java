package network.rest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import structures.IDataAccessor;
import logic.qr.QRCode;
import network.security.AuthenticationValidator;
import network.security.UserService;
import network.security.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import network.security.Credentials;
import structures.User;
import structures.UserInfo;
import structures.UserSecuredInfo;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/")
public class UserControler
{
    private IDataAccessor _dbAccessor;

    private AuthenticationValidator authenticationValidator;

    public void setDatabaseController(IDataAccessor db)
    {
        _dbAccessor = db;
    }

    public UserControler()
    {
        authenticationValidator = new AuthenticationValidator();
    }

    @PostMapping(path = "user/token", consumes = "application/json")
    public String login(@RequestBody Credentials creds)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            ArrayNode node = mapper.createArrayNode();

            ObjectNode oNode = mapper.createObjectNode();
            oNode.put("token", (new UserService()).login(creds.getUsername(), creds.getPassword(), _dbAccessor));
            oNode.put("id", ((User)_dbAccessor.getUserByCredentials(creds.getUsername())).getUserId());

            node.add(oNode);

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        }
        catch(InvalidCredentialsException e)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access)");
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/mail/{email}")
    public UserInfo getUserByMail(@PathVariable String email, @RequestHeader (name="Authorization") String token) {

        if(authenticationValidator.verifyToken(token, email, _dbAccessor))
        {
            User user = (User)_dbAccessor.getUserByMail(email);

            if(user != null)
            {
                return user.getInfos();
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access forbidden");
    }

    @GetMapping("user/credits/{id}")
    public int getCredits(@PathVariable int id, @RequestHeader (name="Authorization") String token)
    {
        if(authenticationValidator.verifyToken(token, id, _dbAccessor))
        {
            User user = (User)_dbAccessor.getUserById(id);

            if(user != null)
            {
                return user.getNumberCredits();
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
}
    @GetMapping("user/{id}")
    public UserInfo getUserById(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        if(authenticationValidator.verifyToken(token, id, _dbAccessor))
        {
            User user = (User)_dbAccessor.getUserById(id);

            if(user != null)
            {
                return user.getInfos();
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access forbidden");
    }

    @PostMapping(path = "user/")
    public void addMember(@RequestBody UserSecuredInfo infos)
    {
        System.out.println();
        if(infos != null)
        {
            User res = (User)_dbAccessor.addUser(infos);

            if(res == null)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't create such an user");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong user format");
        }
    }

    @GetMapping(path = "user/qrcode/{id}")
    public String getQRCode(@PathVariable int id, @RequestHeader (name="Authorization") String token)
    {
        if(authenticationValidator.verifyToken(token, id, _dbAccessor))
        {
            QRCode qr = QRCode.random();

            try {
                _dbAccessor.setQRCode(id, qr.toString());
            } catch (SQLException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            }

            return qr.toString();
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access forbidden");
    }

    @PostMapping(path = "machine/validateQR")
    public void validateQr(@RequestBody String qr)
    {
        if(qr != null)
        {
            User u = _dbAccessor.isQRCodeValid(qr);

            if(u != null)
            {
                throw new ResponseStatusException(HttpStatus.OK, String.valueOf(u.getUserId()));
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");

    }

    @GetMapping("machine/auth/{id}")
    public void authorize(@PathVariable int id) {
        User user = (User)_dbAccessor.getUserById(id);

        if(user != null)
        {
            if(user.getNumberCredits() > 0)
            {
                throw new ResponseStatusException(HttpStatus.OK, "");
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
        }
    }

    @GetMapping("machine/valD/{id}")
    public void validateDist(@PathVariable int id) {
        User user = (User)_dbAccessor.getUserById(id);

        if(user != null)
        {
            user.setNumberCredits(user.getNumberCredits() -1);
            _dbAccessor.updateUser(user);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
        }
        return null;
    }

    @GetMapping("machine/valR/{id}")
    public void validateRec(@PathVariable int id) {
        User user = (User)_dbAccessor.getUserById(id);

        if(user != null)
        {
            user.setNumberCredits(user.getNumberCredits() +1);
            _dbAccessor.updateUser(user);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user not found");
        }
        return null;
    }

}
