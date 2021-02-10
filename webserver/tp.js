var express = require('express');
var session = require('cookie-session'); // Charge le middleware de sessions
var bodyParser = require('body-parser'); // Charge le middleware de gestion des paramètres
var urlencodedParser = bodyParser.urlencoded({ extended: false });
var fs = require('fs');

const sqlite3 = require('sqlite3').verbose();

//fonction sur la base de donnée access pour vérifier id/pass
//data pour accéder à id et numberofcredits
//supprime pour supprimer un compte
//add pour ajouter un compte
//change pour changer les id, name, number, mail
//changepass pour changer le password

function access_admin(id,pass){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
		if(err){
			console.error(err.message);
		}
		console.log('Connected to the Recocup\'s database.');
	});
	let sql = `SELECT adminName id, password pass FROM Admins WHERE id = ? `;
	db.each(sql, [id], (err, row) => {
		if (err) {
			throw err;
		};
		if(row.pass == password){
			callback(null, 1);
		};
		});
	db.close();
	console.log('Deconnected from the database');
	}




function access(id,password, callback){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
		console.log('Connected to the Recocup\'s database.');
		let sql = `SELECT userId, hashPassword pass FROM Users WHERE userId = ? `;
		db.each(sql, [id], (err, row) => {
			if(row.pass == password){
				console.log(1);
				callback(1);
				console.log(`${row.id} - ${row.pass}`);
				console.log('?')
			}else
			{
callback(0);
			}
			
			console.log('??????');
			db.close();
			console.log('Deconnected from the database');
		});
		
	});
	
}



 function id_libre(table, table_id){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
		if(err){
			console.error(err.message);
		}
		console.log('Connected to the Recocup\'s database.');
	});
	let sql = `SELECT ? id FROM ? Ordet by ?`
	db.each(sql, [table_id, table, table_id], (err, row) =>{
		return row.id +1 ;
	});
}



function add_admin(id,name,password, id_Machine){
	let db = new sqlite3.Database("../data/test.db", sqlite3.OPEN_READWRITE, (err) => {
		if (err) {
			console.error(err.message);
		}
		console.log('Connected to the recocup\'s database.');
		let sql = `INSERT INTO Administrateurs(adminId, adminName, password, idMachine) VALUES(?,?,?,?)`;
		db.run(sql,[id,name,password,id_Machine],function(err){
			if(err){
				return console.log(err.message);
			}
			console.log('Added succesfully');
		})
	});
	db.close();
	console.log('Deconnected from the database');
}




function data_admin(id,pass){
	if(access(id,pass)){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
		if(err){
			console.error(err.message);
		};
		console.log('Connected to the Recocup\'s database.');
	});
	let sql = `SELECT machineId id , adminName name FROM Administrateurs WHERE admin_id = ?`;
	db.each(sql, [id], (err, row) => {
		if(err){
			throw err;
		}
		req.session.id = row.id;
		req.session.name =row.name;
	});
	let sql2 = `SELECT numberEcocup nb, Localisation locFROM Machines WHERE machineId = ?`;
	db.each(sql2,[req.session.id], (err, row) => {
		if(err){
			throw err;
		}
		else{
			req.session.nb = row.nb;
			req.session.loc = row.loc;
		}
	});
	db.close();
	console.log('Deconnected from the database');}
}

function machine_existe(id){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
		if(err){
			console.error(err.message);
		};
		console.log('Connected to the Recocup\'s database.');
	});
	let sql = `SELECT * FROM Machines WHERE machineId = ?`;
	db.get(sql, [id], (err, row) =>{
		if(row==undefined){
			return 0;}
		else{
			return 1;
		}
	});
	db.close();
	console.log('Deconnected from the database');}






	function add_machine(id_Machine,localisation, nb, password){
		let db = new sqlite3.Database("../data/test.db", sqlite3.OPEN_READWRITE, (err) => {
			if (err) {
				console.error(err.message);
			}
			console.log('Connected to the recocup\'s database.');
			let sql = `INSERT INTO Machines(machineId, localisation, numberEcocup, password) VALUES(?,?,?,?)`;
			db.run(sql,[id_Machine,localisation, nb, password],function(err){
				if(err){
					return console.log(err.message);
				}
				console.log('Added succesfully');
			})
		});
		db.close();
		console.log('Deconnected from the database');
	}







function data(id,pass, callback){
	access(id,pass, function(acc)
	{
		if(acc)
		{
			let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READONLY, (err) => {
				if(err){
					console.error(err.message);
				};
				console.log('Connected to the Recocup\'s database.');
				let sql = `SELECT userId id, numberCredits credits FROM Users WHERE id = ?`;
			db.each(sql, [id], (err, row) => {
				if(err){
					throw err;
				}
				callback(row.credits)
				console.log(`${row.id} -- ${row.credits}`);
				db.close();
			console.log('Deconnected from the database');
			});
			});
			
			
		}
	})
	
}







function supprime(id){
	let db = new sqlite3.Database('../data/test.db', sqlite3.OPEN_READWRITE, (err) => {
		if(err){
			console.error(err.message);
		};
		console.log('Connected to the Recocup\'s Database');
});
	let sql = `DELETE FROM Users WHERE userID =?`;
	db.run(sql,[id],function(err){
		if (err) {
			return console.error(err.message);
		}
		else {
			return console.log('deleted succesfully');
		}
	});
	db.close();
	console.log('Deconnected from the database');
}







function add(id,name,mail,pass){
	let db = new sqlite3.Database("../data/test.db", sqlite3.OPEN_READWRITE, (err) => {
		if (err) {
			console.error(err.message);
		}
		console.log('Connected to the recocup\'s database.');
		let sql = `INSERT INTO Users(userId,userName,userMail,hashPassword,numberCredits,QRcode) VALUES(?,?,?,?,2,null)`;
		db.run(sql,[id,name,mail,pass],function(err){
			if(err){
				return console.log(err.message);
			}
			console.log('Added succesfully');
		})
	});
	db.close();
	console.log('Deconnected from the database');
}







function change(id,name,mail,number){
		let db = new sqlite3.Database("../data/test.db", sqlite3.OPEN_READWRITE, (err) => {
		if (err) {
			console.error(err.message);
		}
		console.log('Connected to the recocup\'s database.');
		let sql = `UPDATE Users id =?, name = ?, mail =? , number =? WHERE id =?`;
		db.run(sql,[id,name,mail,number,req.sesion.id],function(err){
			if(err){
				return console.log(err.message);
			}
			console.log('changed succesfully');
		})
	});
	db.close();
	console.log('Deconnected from the database');
}








function changepass(id,pass, newpass){
	if(access(id,pass)){
		let db = new sqlite3.Database("../data/test.db", sqlite3.OPEN_READWRITE, (err) => {
		if (err) {
			console.error(err.message);
		}
		console.log('Connected to the recocup\'s database.');
		let sql = `UPDATE Users hashPassword=? WHERE id =?`;
		db.run(sql,[newpass,userId],function(err){
			if(err){
				return console.log(err.message);
			}
			console.log('changed succesfully');
		})
	});
	db.close();
	console.log('Deconnected from the database');
}
else{console.log("Wrong id/password");
}}







var app = express();
app.use( express.static( "public" ) );
app.use(session({secret: 'idsecret'}))



    app.get('/', function(req,res){
    	res.render('accueil.ejs');
    })

	
	app.get('/connection_admin', function(req,res){
		res.render('admin.ejs');
	})

	app.post('/connection/se_connecte_admin/' ,urlencodedParser, function(req,res){
    	if(access_admin(req.body.id,req.body.password)){
    		req.session.id = req.body.id;
    		req.session.password = req.body.password;
    		console.log(req.session.id);
    		console.log(req.session.password);
    		res.redirect('/connection/'+req.session.id+'/'+req.session.password+'/');
    	}
    	else {
			res.render('connection.ejs');
		}
	})


	app.get('/admin/:id/:password', function(req, res){
		data_admin(req.session.id,req.session.password);
		res.render('admin_session.ejs' , {machine : req.session.id, id : req.session.name, localisation : req.session.loc, nb : req.session.nb})
	})


	app.get('/inscription_admin', function(req,res){
		res.render('inscription_admin.ejs');
	})


	app.get('/inscription_admin_m', function(req,res){
		res.render('inscription_admin_m.ejs');
	})


	app.post("inscription_admin_m/inscrire", urlencodedParser, function(req,res){
		if(machine_existe(req.body.id_Machine)){
			if(req.body.vpassword != req.body.password){
    			res.render('faili.ejs');

			}
			else{
				req.session.password = req.body.password;
				req.session.id_Machine = req.body.id_Machine;
				req.session.name = req.body.id;
				req.session.id = id_libre(Administrateurs, adminId);
				add_admin(req.session.id, req.session.name, req.session.password, req.session.id_Machine);
			}

		}
	})

	app.post("inscription_admin_nm/inscrire", urlencodedParser, function(req,res){
			if(req.body.vpassword != req.body.password){
    			res.render('faili.ejs');

			}
			else{
				req.session.password = req.body.password;
				req.session.name = req.body.id;
				req.session.id = id_libre(Administrateurs, adminId);
				req.session.id_Machine = id_libre(Machines, machineId);
				req.session.localisation = req.body.localisation;
				req.session.nb = req.body.nb
				req.session.machine_password = req.body.machine_password;
				add_admin(req.session.id, req.session.name, req.session.password, req.session.id_Machine);
				add_machine(req.session.id_Machine, req.session.localisation, req.session.nb, req.session.password);
			}
	})



	app.get('/inscription_admin_nm', function(req,res){
		res.render('inscription_admin_nm.ejs');
	})



    app.get('/connection', function(req,res){
    	res.render('connection.ejs');
	})



    app.post('/connection/se_connecte/' ,urlencodedParser, function(req,res){
		
		access(req.body.id,req.body.password, function(acc) 
		{
			if(acc){
			console.log("what")
    		req.session.id = req.body.id;
    		req.session.password = req.body.password;
    		console.log(req.session.id);
    		console.log(req.session.password);
    		res.redirect('/connection/'+req.session.id+'/'+req.session.password+'/');
    	}
		else 
		{
			res.render('connection.ejs');
		}});
	})



    app.get('/connection/:id/:password', function(req,res){
		data(req.session.id,req.session.password, function(ans)
		{
			res.render('user.ejs',{id : req.session.id , password : req.session.password, credits : ans});
		});
    	
    })



    app.get('/inscription', function(req,res){
    	res.render('inscription.ejs');
    })



    app.post('/inscription/s_inscrire/', urlencodedParser, function(req,res){
    			req.session.id = req.body.id;
    			req.session.password = req.body.password;
    	    	req.session.vpassword = req.body.vpassword;
    	    	req.session.name = req.body.name;
    	    	req.session.email = req.body.email ;
    	    	req.session.number = req.body.number;

    		if(req.session.vpassword != req.session.password){
    			res.render('faili.ejs');

    		}

    		else{
    			add(req.session.id,req.session.name,req.session.email,req.session.password);
    			res.redirect('/connection/'+req.session.id +'/'+ req.session.password + '/');
    		}
    })




	app.post('/connection/:id/:password/supprimer', function(req,res){
	supprime(req.session.id);
	res.render('connection.ejs');
})


	app.get('/account', function(req,res){
		res.render('account.ejs');
	})


	app.get('/change', function(req,res){
		res.render('change.ejs');
	})


	app.get('/changepass', function(req,res){
		res.render('changepass.ejs');
	})


	app.post('/change/changing', function(req,res){
		change(req.session.id,req.body.name,req.body.mail,req.body.number);
		res.redirect('/');
	})

	app.use('/', express.static('public'));

    app.listen(8081);
