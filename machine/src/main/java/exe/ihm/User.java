package exe.ihm;

    public class User
    {

        private String _username;
        private String _email;
        private int _id;
        private int _credits;

        public User(String username, String email, int id, int credits)
        {
            this._username = username;
            this._email = email;
            this._id = id;

            this._credits = credits;
        }

        public String getUsername()
        {
            return _username;
        }

        public String getEmail()
        {
            return _email;
        }

        public int getId()
        {
            return _id;
        }

        public int getCredits()
        {
            return _credits;
        }

    }


