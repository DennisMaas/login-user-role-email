# login-user-role-email

Demo project.
This is a backend application that let´s users register with full name, emailadress and password and grant´s them the role user.
Once the users have entered their information, they will get an email with a link to confirm registration.
The data will be stored in a postgreSQL database and the password is being encoded with BCryptPasswordEncoder.


I am using https://github.com/maildev/maildev to send and receive the emails, so make sure to install it using npm.
```
$ npm install -g maildev
$ maildev
```
you can see the incoming confirmation emails on `localhost:1080`