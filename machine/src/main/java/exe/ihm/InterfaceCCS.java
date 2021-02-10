package exe.ihm;

import exe.ihm.*;

import java.io.IOException;

public interface InterfaceCCS {

        public int verifyQRCode(String qrcode) throws IOException;

        public User getUser(int userID);
        public User getUser(String email);

        public boolean authorizeDistribution(int user) throws IOException;
        public void validateDistribution(int user) throws IOException;

        public void validateCupRecover(int user) throws IOException;

        public void validateCupRecover(int user) throws IOException;
        public int logUser(String username, String password);
}
