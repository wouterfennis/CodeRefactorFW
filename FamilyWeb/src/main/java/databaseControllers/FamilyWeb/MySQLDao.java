package databaseControllers.FamilyWeb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Answer;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Result;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

/**
 * The Class MySQLDao.
 */
public class MySQLDao implements DatabaseInterface {
	
	/** The username to login into the database. */
	private String username = "root";
	
	/** The password to login into the database. */
	private String passwd = "wachtwoord";
	
	/** The database location. */
	private String dbLocation = "jdbc:mysql://localhost/familyweb";

	/**
	 * Creates the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbLocation, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("failed to connect to database");
		}
		return conn;
	}

	/**
	 * Close the connection.
	 *
	 * @param conn the connection
	 */
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addUser(domain.FamilyWeb.User)
	 */
	public boolean addUser(User user) {
		Connection conn = null;
		boolean b = false;
		try {
			// create connection
			conn = this.getConnection();
			
			// setup query to insert an new User
			PreparedStatement pStmt = conn
					.prepareStatement("insert into users(username,password,forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,usertype,email,isactive,employeenumber,wwreset) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			// set values in query
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getForename());
			pStmt.setString(4, user.getSurname());
			pStmt.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
			pStmt.setString(6, user.getPostcode());
			pStmt.setString(7, user.getStreet());
			pStmt.setString(8, user.getHouseNumber());
			pStmt.setString(9, user.getCity());
			pStmt.setString(10, user.getNationality());
			pStmt.setString(11, user.getTelephoneNumber());
			pStmt.setString(12, user.getMobilePhoneNumber());
			pStmt.setString(13,
					(user instanceof Administrator) ? "Administrator"
							: "Socialworker");
			pStmt.setString(14, user.getEmail());
			pStmt.setString(15, user.isActive() ? "Y" : "N");
			pStmt.setString(16, user.getEmployeeNumber());
			pStmt.setString(17, "Y");
			
			// execute query to the connection with database
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	/**
	 * Check if username exists.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean checkUsername(String username){
		boolean b = true;
		Connection conn = null;
		try {
			// create connection
			conn = this.getConnection();
			
			// setup query to check if username exists
			PreparedStatement pStmt = conn.prepareStatement("select * from users where username=?");
			
			// set value username
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			
			// check if username exists
			if (rSet.next())
				b = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// return result
		return b;
	}
	
	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getUser(java.lang.String)
	 */
	public User getUser(String username) {
		User user = null;
		Connection conn = null;
		try {
			// create connection
			conn = this.getConnection();
			
			// setup query to get user from username
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users where username=?");
			
			// set value of username
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			
			// if record exist, make the user object
			if (rSet.next()) {
				//check which usertype the user is
				if (rSet.getString("usertype").equals("Administrator")) {
					// if usertype is administrator, make administrator object
					user = new Administrator(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"),
							new ArrayList<User>());
					// set user id, which isn't included in the constructor 
					user.setUser_id(rSet.getInt("user_id"));
					
				} else {
					// if usertype is socialworker, make socialworker object
					user = new Socialworker(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"));

					// set user id, which isn't included in the constructor 
					user.setUser_id(rSet.getInt("user_id"));
				}
				// set this database interface onto the user
				user.setDbController(this);
				// set if password needs to reset
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// set users or clients by the User
		if (user instanceof Administrator) {
			Administrator admin = (Administrator) user;
			admin.setUsers(getAllSocialworkers());
		}else{
			Socialworker soc =  (Socialworker) user;
			soc.setMyClients(this.getAllClientsOfUser(user));
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#updateUser(domain.FamilyWeb.User)
	 */
	public boolean updateUser(User user) {
		Connection conn = null;
		boolean b = false;
		try {
			// create connection
			conn = this.getConnection();
			
			// setup query to update values into the database from the user
			PreparedStatement pStmt = conn
					.prepareStatement("update users set password=?,forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,usertype=?,email=?,isactive=?,employeenumber=?, wwreset=? where username=?");
			// set values
			pStmt.setString(1, user.getPassword());
			pStmt.setString(2, user.getForename());
			pStmt.setString(3, user.getSurname());
			pStmt.setDate(4, new java.sql.Date(user.getDateOfBirth().getTime()));
			pStmt.setString(5, user.getPostcode());
			pStmt.setString(6, user.getStreet());
			pStmt.setString(7, user.getHouseNumber());
			pStmt.setString(8, user.getCity());
			pStmt.setString(9, user.getNationality());
			pStmt.setString(10, user.getTelephoneNumber());
			pStmt.setString(11, user.getMobilePhoneNumber());
			pStmt.setString(12,
					(user instanceof Administrator) ? "Administrator"
							: "Socialworker");
			pStmt.setString(13, user.getEmail());
			pStmt.setString(14, user.isActive() ? "Y" : "N");
			pStmt.setString(15, user.getEmployeeNumber());
			pStmt.setString(16, user.isWwreset() ? "Y" : "N");
			pStmt.setString(17, user.getUsername());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#authentication(java.lang.String, java.lang.String)
	 */
	public boolean authentication(String username, String password) {
		boolean auth = false;
		Connection conn = null;
		try {
			// create connection
			conn = this.getConnection();
			//setup query to check if username and password combination isn't right
			PreparedStatement pStmt = conn
					.prepareStatement("select username, password from users where username=? AND password=?");
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			ResultSet rSet = pStmt.executeQuery();
			// if the combination exists the the authentication is correct
			if (rSet.next()) {
				auth = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return auth;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getAllSocialworkers()
	 */
	public ArrayList<User> getAllSocialworkers() {
		Connection conn = null;
		// intialize the arraylist
		ArrayList<User> users = new ArrayList<User>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all users with usertype socialworker
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users where usertype=?");
			pStmt.setString(1, "Socialworker");
			ResultSet rSet = pStmt.executeQuery();
			// for every socialworker, create new user and add it to the arraylist
			while (rSet.next()) {
				User user = new Socialworker(rSet.getString("username"),
						rSet.getString("password"), rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("isActive")
								.equals("Y"), rSet.getString("employeeNumber"));
				// set this database interface to the user
				user.setDbController(this);
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
				// add user to the arraylist
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return users;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addClient(domain.FamilyWeb.Client, int)
	 */
	public boolean addClient(Client client, int user_id) {
		Connection conn = null;
		boolean b = false;
		try {
			// create connection
			conn = this.getConnection();
			
			// setup query to insert the client with the right user as Foreign Key
			PreparedStatement pStmt = conn
					.prepareStatement("insert into clients(forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,email,filenumber,dateCreated, user_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pStmt.setString(1, client.getForename());
			pStmt.setString(2, client.getSurname());
			pStmt.setDate(3, new java.sql.Date(client.getDateOfBirth().getTime()));
			pStmt.setString(4, client.getPostcode());
			pStmt.setString(5, client.getStreet());
			pStmt.setString(6, client.getHouseNumber());
			pStmt.setString(7, client.getCity());
			pStmt.setString(8, client.getNationality());
			pStmt.setString(9, client.getTelephoneNumber());
			pStmt.setString(10, client.getMobilePhoneNumber());
			pStmt.setString(11, client.getEmail());
			pStmt.setString(12, client.getFileNumber()); 
			pStmt.setDate(13, new java.sql.Date(client.getDateCreated().getTime()));
			pStmt.setInt(14, user_id);
			// execute query
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each family member connected to the client, insert each family member
		for (Familymember f : client.getMyFamilymembers())
			b &= this.addFamilymember(f, client);
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#updateClient(domain.FamilyWeb.Client, int)
	 */
	public boolean updateClient(Client client, int user_id) {
		Connection conn = null;
		boolean b = false;
		
		try {
			// create connection
			conn = this.getConnection();
			PreparedStatement pStmt = null;
			// if user_id equals 0, don't update user id
			if(user_id == 0)
				pStmt = conn
					.prepareStatement("update clients set forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,email=?, filenumber=?,dateCreated=? where client_id=?");
			else
				// also update user id
				pStmt = conn
				.prepareStatement("update clients set forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,email=?, filenumber=?,dateCreated=?, user_id=? where client_id=?");
			// set values into query
			pStmt.setString(1, client.getForename());
			pStmt.setString(2, client.getSurname());
			pStmt.setDate(3, new java.sql.Date(client.getDateOfBirth()
					.getTime()));
			pStmt.setString(4, client.getPostcode());
			pStmt.setString(5, client.getStreet());
			pStmt.setString(6, client.getHouseNumber());
			pStmt.setString(7, client.getCity());
			pStmt.setString(8, client.getNationality());
			pStmt.setString(9, client.getTelephoneNumber());
			pStmt.setString(10, client.getMobilePhoneNumber());
			pStmt.setString(11, client.getEmail());
			pStmt.setString(12, client.getFileNumber());
			pStmt.setDate(13, new java.sql.Date(client.getDateCreated()
					.getTime()));
			if(user_id == 0)
				pStmt.setInt(14, client.getClient_id());
			else{
				pStmt.setInt(14, user_id);
				pStmt.setInt(15, client.getClient_id());
			}
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each familymember connected to the client, update each familymember
		for (Familymember f : client.getMyFamilymembers())
			this.updateFamilymember(f);
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getAllClientsOfUser(domain.FamilyWeb.User)
	 */
	public ArrayList<Client> getAllClientsOfUser(User user) {
		Connection conn = null;
		// initialize arraylist
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all clients with the same user_id as the user_id from the user
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients where user_id=?");
			pStmt.setInt(1, user.getUser_id());
			ResultSet rSet = pStmt.executeQuery();
			// for each client found, create client object
			while (rSet.next()) {
				Client client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("fileNumber"),
						rSet.getDate("dateCreated"));
				client.setClient_id(rSet.getInt("client_id"));
				// add the client to arraylist
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each client found, get also the familymembers
		for (Client c : clients)
			c.setMyFamilymembers(this.getFamilymembersOfClient(c));
		return clients;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addFamilymember(domain.FamilyWeb.Familymember, domain.FamilyWeb.Client)
	 */
	public boolean addFamilymember(Familymember famMember, Client client) {
		Connection conn = null;
		boolean b = false;
		try {
			// create connection
			conn = this.getConnection();
			//setup query to insert family member into the database, and connect familymember with the client by the client_id
			PreparedStatement pStmt = conn
					.prepareStatement("insert into familymembers(forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,email,client_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			// set each value
			pStmt.setString(1, famMember.getForename());
			pStmt.setString(2, famMember.getSurname());
			java.sql.Date birth = famMember.getDateOfBirth();
			if(birth == null)				
				pStmt.setDate(3, null);
			else
				pStmt.setDate(3, new Date(birth.getTime()));
			pStmt.setString(4, famMember.getPostcode());
			pStmt.setString(5, famMember.getStreet());
			pStmt.setString(6, famMember.getHouseNumber());
			pStmt.setString(7, famMember.getCity());
			pStmt.setString(8, famMember.getNationality());
			pStmt.setString(9, famMember.getTelephoneNumber());
			pStmt.setString(10, famMember.getMobilePhoneNumber());
			pStmt.setString(11, famMember.getEmail());
			pStmt.setInt(12, client.getClient_id());
			// execute query
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#updateFamilymember(domain.FamilyWeb.Familymember)
	 */
	public boolean updateFamilymember(Familymember famMember) {
		Connection conn = null;
		boolean b = false;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to update familymember
			PreparedStatement pStmt = conn
					.prepareStatement("update familymembers set forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,email=? where member_id=?");
			// set value
			pStmt.setString(1, famMember.getForename());
			pStmt.setString(2, famMember.getSurname());
			pStmt.setDate(3, new java.sql.Date(famMember.getDateOfBirth()
					.getTime()));
			pStmt.setString(4, famMember.getPostcode());
			pStmt.setString(5, famMember.getStreet());
			pStmt.setString(6, famMember.getHouseNumber());
			pStmt.setString(7, famMember.getCity());
			pStmt.setString(8, famMember.getNationality());
			pStmt.setString(9, famMember.getTelephoneNumber());
			pStmt.setString(10, famMember.getMobilePhoneNumber());
			pStmt.setString(11, famMember.getEmail());
			pStmt.setInt(12, famMember.getMember_id());
			// execute query
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getFamilymembersOfClient(domain.FamilyWeb.Client)
	 */
	public ArrayList<Familymember> getFamilymembersOfClient(Client client) {
		Connection conn = null;
		//initialize arraylist
		ArrayList<Familymember> members = new ArrayList<Familymember>();
		try {
			// create query
			conn = this.getConnection();
			// setup query to get all familymembers that are connected to the client by the cleint_id
			PreparedStatement pStmt = conn
					.prepareStatement("select * from familymembers where client_id=?");
			pStmt.setInt(1, client.getClient_id());
			ResultSet rSet = pStmt.executeQuery();
			// for each familymember found, create familymember object an add it to the arraylist
			while (rSet.next()) {
				Familymember member = new Familymember(
						rSet.getString("forename"), rSet.getString("surname"),
						rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"));
				// also set the member id, which isn't set in the constructor
				member.setMember_id(rSet.getInt("member_id"));
				// add member to arraylist
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return members;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getClient(int)
	 */
	public Client getClient(int client_id) {
		Connection conn = null;
		Client client = null;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get the client equal to an integer as client_id
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients where client_id=?");
			pStmt.setInt(1, client_id);
			ResultSet rSet = pStmt.executeQuery();
			// if client exists, create client object
			if (rSet.next()) {
				client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("filenumber"),
						rSet.getDate("dateCreated"));
				client.setClient_id(rSet.getInt("client_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// if client was created, get also all familymembers connected to the client
		if(client != null)
			client.setMyFamilymembers(this.getFamilymembersOfClient(client));
		return client;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addSurvey(domain.FamilyWeb.Survey)
	 */
	public boolean addSurvey(Survey survey) {
		Connection conn = null;
		boolean b = true;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to insert an new survey
			PreparedStatement pStmt = conn
					.prepareStatement("insert into surveys(name) values(?)");
			pStmt.setString(1, survey.getName());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		// if first query was executed well, then go further, else stop the method and give false back
		if (b) {
			try {
				// create new connection
				conn = this.getConnection();
				// setup query to get survey id
				PreparedStatement pStmt = conn
						.prepareStatement("select * from surveys where name=?");
				pStmt.setString(1, survey.getName());
				ResultSet rSet = pStmt.executeQuery();
				// get survey_id
				if (rSet.next()) {
					survey.setSurvey_id(rSet.getInt("survey_id"));
				} else
					b = false;
			} catch (SQLException e) {
				e.printStackTrace();
				b = false;
			} finally {
				this.closeConnection(conn);
			}
			// for each question in the survey, connect the question existed in the database to the surey
			for (Question q : survey.getQuestions()) {
				int question_id = 0;
				b &= this.addQuestion(q);
				if (b) {
					try {
						// create new connection
						conn = this.getConnection();
						// get every question existed in the database
						PreparedStatement pStmt = conn
								.prepareStatement("select * from questions where question=?");
						pStmt.setString(1, q.getQuestion());
						ResultSet rSet = pStmt.executeQuery();
						if (rSet.next()) {
							question_id = rSet.getInt("question_id");
						} else
							b = false;
					} catch (SQLException e) {
						e.printStackTrace();
						b = false;
					} finally {
						this.closeConnection(conn);
					}
				}
				if (b) {
					try {
						// create new connection
						conn = this.getConnection();
						// setup query to connect questions to the survey
						PreparedStatement pStmt = conn
								.prepareStatement("insert into surveys_questions(survey_id, question_id) values(?,?)");
						pStmt.setInt(1, survey.getSurvey_id());
						pStmt.setInt(2, question_id);
						pStmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						b = false;
					} finally {
						this.closeConnection(conn);
					}
				}
			}
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#updateSurvey(domain.FamilyWeb.Survey)
	 */
	public boolean updateSurvey(Survey survey) {
		Connection conn = null;
		boolean b = true;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to update the survey name
			PreparedStatement pStmt = conn
					.prepareStatement("update surveys set name=? where survey_id=?");
			pStmt.setString(1, survey.getName());
			pStmt.setInt(2, survey.getSurvey_id());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		// if first query was executed well, go further
		if (b) {
			// for each question from the survey, update those questions
			for (Question q : survey.getQuestions()) {
				this.updateQuestion(q);
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getSurvey(java.lang.String)
	 */
	public Survey getSurvey(String surveyName) {
		Survey survey = null;
		Connection conn = null;
		// initialize arraylist
		ArrayList<Integer> questionIDs = new ArrayList<Integer>();
		boolean b = true;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get the survey by name
			PreparedStatement pStmt = conn
					.prepareStatement("select * from surveys where name=?");
			pStmt.setString(1, surveyName);
			ResultSet rSet = pStmt.executeQuery();
			// if exists, then create survey object
			if (rSet.next()) {
				survey = new Survey(rSet.getString("name"), null);
				survey.setSurvey_id(rSet.getInt("survey_id"));
			} else
				b = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// if survey exists, then get each question connected to the survey
		if (b) {
			try {
				// create new connection
				conn = this.getConnection();
				// setup query to get each question connected to the survey
				PreparedStatement pStmt = conn
						.prepareStatement("select * from surveys_questions where survey_id=?");
				pStmt.setInt(1, survey.getSurvey_id());
				ResultSet rSet = pStmt.executeQuery();
				while (rSet.next()) {
					questionIDs.add(rSet.getInt("question_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				b = false;
			} finally {
				this.closeConnection(conn);
			}
		}
		if (b) {
			// create question objects rom each question connected to the survey
			ArrayList<Question> questions = new ArrayList<Question>();
			for (int i : questionIDs) {
				questions.add(getQuestion(i));
			}
			survey.setQuestions(questions);
		}
		// if survey exists return that survey, else returns null
		return (b) ? survey : null;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addQuestion(domain.FamilyWeb.Question)
	 */
	public boolean addQuestion(Question question) {
		Connection conn = null;
		boolean b = true;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to insert questions
			PreparedStatement pStmt = conn
					.prepareStatement("insert into questions(question) values(?)");
			pStmt.setString(1, question.getQuestion());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		// if inserting went well, then go further
		if (b) {
			// for each answer for the question insert htat answer
			for (Answer answer : question.getTheAnswers()) {
				try {
					// create new connection
					conn = this.getConnection();
					// setup query to insert answer into database 
					PreparedStatement pStmt = conn
							.prepareStatement("insert into answers(answer, question_id) values(?,?)");
					pStmt.setString(1, answer.getAnswer());
					pStmt.setInt(2, question.getQuestion_id());
					pStmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					b = false;
				} finally {
					this.closeConnection(conn);
				}
			}
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#updateQuestion(domain.FamilyWeb.Question)
	 */
	public boolean updateQuestion(Question question) {
		Connection conn = null;
		boolean b = true;
		try {
			// create connection
			conn = this.getConnection();
			// setup query to update the question 
			PreparedStatement pStmt = conn
					.prepareStatement("update questions set question=? where question_id=?");
			pStmt.setString(1, question.getQuestion());
			pStmt.setInt(2, question.getQuestion_id());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		// if the first update went well, then update each answer from the question
		if (b) {
			for (Answer answer : question.getTheAnswers()) {
				try {
					// create new question
					conn = this.getConnection();
					// setup query to update an answer
					PreparedStatement pStmt = conn
							.prepareStatement("update answers set answer=?, question_id=? where answer_id=?");
					pStmt.setString(1, answer.getAnswer());
					pStmt.setInt(2, question.getQuestion_id());
					pStmt.setInt(3, answer.getAnswer_id());
					pStmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					b = false;
				} finally {
					this.closeConnection(conn);
				}
			}
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getQuestion(int)
	 */
	public Question getQuestion(int question_id) {
		Question question = null;
		Connection conn = null;
		// initialize arraylist
		ArrayList<Answer> answers = new ArrayList<Answer>();
		try {
			// create new connection
			conn = this.getConnection();
			// setup query to get alle questions equal to the integer 
			PreparedStatement pStmt = conn
					.prepareStatement("select * from questions where question_id=?");
			pStmt.setInt(1, question_id);
			ResultSet rSet = pStmt.executeQuery();
			// if exits, create new question object
			if (rSet.next()) {
				question = new Question(question_id,
						rSet.getString("question"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// if question exists than also get each answer
		if(question != null){
			try {
				// create conenction
				conn = this.getConnection();
				// setup query to get all answer for the question
				PreparedStatement pStmt = conn
						.prepareStatement("select * from answers where question_id=?");
				pStmt.setInt(1, question_id);
				ResultSet rSet = pStmt.executeQuery();
				// for each answer found, create answer objects and add it to the arraylist
				while (rSet.next()) {
					answers.add(new Answer(rSet.getString("answer"), rSet
							.getInt("answer_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.closeConnection(conn);
			}
			// set answers to the question earlier created
			question.setTheAnswers(answers);
		}		
		return question;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getAllClients()
	 */
	public ArrayList<Client> getAllClients() {
		Connection conn = null;
		// intialize arraylist
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all clients
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients");
			ResultSet rSet = pStmt.executeQuery();
			// for each client found, create client object
			while (rSet.next()) {
				Client client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("fileNumber"),
						rSet.getDate("dateCreated"));
				// set client_id, which isn't included into the constructor
				client.setClient_id(rSet.getInt("client_id"));
				// add new client to arraylist
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each client, get also family members for that client
		for (Client c : clients)
			c.setMyFamilymembers(this.getFamilymembersOfClient(c));
		return clients;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getSurveyNames()
	 */
	public ArrayList<String> getSurveyNames() {
		Connection conn = null;
		// initialize arraylist
		ArrayList<String> names = new ArrayList<String>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all survey names
			PreparedStatement pStmt = conn
					.prepareStatement("select name from surveys");
			ResultSet rSet = pStmt.executeQuery();
			// for each name found, add it to the arraylist
			while (rSet.next()) {
				names.add(rSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return names;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#addNetwork(domain.FamilyWeb.Network, int, int)
	 */
	public boolean addNetwork(Network network, int client_id,
			int familymember_id) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			//create network
			pStmt = conn.prepareStatement("insert into networks(datecreated, commentary,client_id,member_id,survey_id) values(?,?,?,?,?)");
			pStmt.setDate(1, network.getDateCreated());
			pStmt.setString(2, network.getCommentary());
			if(client_id == 0)
				pStmt.setString(3, null);
			else
				pStmt.setInt(3, client_id);
			if(familymember_id == 0)
				pStmt.setString(4, null);
			else
				pStmt.setInt(4, familymember_id);
			pStmt.setInt(5, network.getTheSurvey().getSurvey_id());
			pStmt.executeUpdate();
			
			//get auto generated id
			pStmt = conn.prepareStatement("select MAX(network_id) from networks where client_id=? OR member_id=?");
			if(client_id == 0)
				pStmt.setString(1, null);
			else
				pStmt.setInt(1, client_id);
			if(familymember_id == 0)
				pStmt.setString(2, null);
			else
				pStmt.setInt(2, familymember_id);
			
			// set latest generated id from network
			ResultSet rSet = pStmt.executeQuery();
			if(rSet.next())
				network.setNetwork_id(rSet.getInt(1));
			rSet.close();	
			
			//intitialize categories
			HashMap<String,Integer> categories = new HashMap<String,Integer>();
				categories.put("household", 1);
				categories.put("family", 2);
				categories.put("friends", 3);
				categories.put("colleagues", 4);
				categories.put("neighbours", 5);
				categories.put("acquaintance", 6);
				categories.put("education", 7);
				categories.put("club", 8);
				categories.put("religion", 9);
				categories.put("careinstitution", 10);
				categories.put("youthcare", 11);
				categories.put("bureauhalt", 12);
				categories.put("justice", 13);
				//create contacts
			for(Contact c : network.getContacts()){
				pStmt = conn.prepareStatement("insert into contacts(`fullname`, `role`, `age`, `commentary`, `category_id`, `network_id`) values(?,?,?,?,?,?)");
				pStmt.setString(1, c.getFullname());
				pStmt.setString(2, c.getRole());
				pStmt.setInt(3, c.getAge());
				pStmt.setString(4, c.getCommentary());
				pStmt.setInt(5, c.getCategories().get(0).getGroup_id() );
				pStmt.setInt(6, network.getNetwork_id());
				pStmt.executeUpdate();
				
				//get contact_id for results
				pStmt = conn.prepareStatement("select contact_id from contacts where network_id=? AND fullname=?");
				pStmt.setInt(1, network.getNetwork_id());
				pStmt.setString(2, c.getFullname());
				rSet = pStmt.executeQuery();				
				if(rSet.next())
					c.setContact_id(rSet.getInt("contact_id"));
				rSet.close();				
			}			
			// set results
			for(Contact c : network.getContacts()){	        
				for(Result r : c.getMyResults()){
					pStmt = conn.prepareStatement("insert into results(`question_id`, `answer_id`, `contact_id`) values(?,?,?)");
					pStmt.setInt(1, r.getTheQuestion().getQuestion_id());
					pStmt.setInt(2, r.getMyAnswer().getAnswer_id());
					pStmt.setInt(3, c.getContact_id());
					pStmt.executeUpdate();
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}		
		return b;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getNetworks(int, int)
	 */
	public ArrayList<Network> getNetworks(int client_id, int familymember_id) {
		Connection conn = null;
		// initialize arraylist
		ArrayList<Network> networks = new ArrayList<Network>();
		PreparedStatement pStmt = null;
		try {
			// create connection
			conn = this.getConnection();
			// if client != 0 then get the network of an client
			if (client_id != 0) {
				// setup query to get the network from an client
				pStmt = conn
						.prepareStatement("select * from networks INNER JOIN surveys ON networks.survey_id=surveys.survey_id where client_id=?");
				pStmt.setInt(1, client_id);
				
			} 
			// else if familymember_id != 0, get the networks from an family member
			else if (familymember_id != 0) {
				// setup query to get networks from familymember_id
				pStmt = conn
						.prepareStatement("select * from networks INNER JOIN surveys ON networks.survey_id=surveys.survey_id where member_id=?");
				pStmt.setInt(1, familymember_id);

			} 
			// else return an empty network
			else
				return networks;
			ResultSet rSet = pStmt.executeQuery();
			// for each network found, create network object
			while (rSet.next()) {
				Network network = new Network(rSet.getDate("datecreated"),
						rSet.getString("commentary"));
				network.setNetwork_id(rSet.getInt("network_id"));
				network.setTheSurvey(new Survey(rSet.getString("name"),
						new ArrayList<Question>()));
				networks.add(network);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each network found, set the survey used and set the contacts
		for (Network n : networks) {
			// set the right survey
			n.setTheSurvey(this.getSurvey(n.getTheSurvey().getName()));
			// for each contact, get the results of that contact
			n.setContacts(this.setResultsContacts(n));
		}		
		return networks;
	}

	/**
	 * Sets the results contacts.
	 *
	 * @param n the n
	 * @return the array list
	 */
	private ArrayList<Contact> setResultsContacts(Network n) {
		Connection conn = null;
		// initialize arraylist
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			// create connection
			conn = this.getConnection();			
			// for each contact get results and set those results to the contact
			for (Contact c : this.getContacts(n)) {
				// initialize results arraylist
				ArrayList<Result> results = new ArrayList<Result>();
				// setup query to get all results from the contact
				PreparedStatement pStmt = conn
						.prepareStatement("select * from results where contact_id=?");
				pStmt.setInt(1, c.getContact_id());
				ResultSet rSet = pStmt.executeQuery();		
				// for each result found, create result object and add it to the arraylist
				while (rSet.next()) {
					Answer answer=null;
					Question question=null;
					// connect the right question_id from the survey from the network
					for(Question q : n.getTheSurvey().getQuestions())
						if(rSet.getInt("question_id")==q.getQuestion_id())
							question = q;							
					// connect the right answer_id from the survey from the network
					for(Answer a : question.getTheAnswers())
						if(rSet.getInt("answer_id")==a.getAnswer_id())
							answer = a;	
					results.add(new Result(question,answer));				
				}
				// set results to the contact
				c.setMyResults(results);
				contacts.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return contacts;
	}

	/**
	 * Gets the contacts.
	 *
	 * @param n the n
	 * @return the contacts
	 */
	private ArrayList<Contact> getContacts(Network n) {
		Connection conn = null;
		// initialize arraylist
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all contacts from an network
			PreparedStatement pStmt = conn
					.prepareStatement("select * from contacts INNER JOIN categories ON contacts.category_id=categories.category_id where network_id=?");
			pStmt.setInt(1, n.getNetwork_id());
			ResultSet rSet = pStmt.executeQuery();
			// for each contact found, create contact object
			while (rSet.next()) {
				Contact c = new Contact(rSet.getString("fullname"),
						rSet.getString("commentary"), rSet.getString("role"),
						rSet.getInt("age"), rSet.getString("name"),rSet.getInt("category_id"));
				// set contact_id which isn't included in the constructor
				c.setContact_id(rSet.getInt("contact_id"));
				contacts.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return contacts;
	}

	/* (non-Javadoc)
	 * @see databaseControllers.FamilyWeb.DatabaseInterface#getAllUsers()
	 */
	public ArrayList<User> getAllUsers() {
		User user = null;
		Connection conn = null;
		// initialize array list
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			// create connection
			conn = this.getConnection();
			// setup query to get all users
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users");
			ResultSet rSet = pStmt.executeQuery();
			// for each user found, create user object and add them to the arraylist
			while(rSet.next()) {
				// if usertype is administrator, create administrator object
				if (rSet.getString("usertype").equals("Administrator")) {
					user = new Administrator(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"),
							new ArrayList<User>());
					user.setUser_id(rSet.getInt("user_id"));					
				} 
				// if usertype is socialworker, create socialworker object
				else {
					user = new Socialworker(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"));
					user.setUser_id(rSet.getInt("user_id"));
				}
				// set this database interface to the user
				user.setDbController(this);
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
				allUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		// for each user, check if user is administrator, if so : get all socialworkers for that user
		for(User u : allUsers){
			if (u instanceof Administrator) {
				Administrator admin = (Administrator) u;
				admin.setUsers(getAllSocialworkers());
			}		
		}		
		return allUsers;
	}
}