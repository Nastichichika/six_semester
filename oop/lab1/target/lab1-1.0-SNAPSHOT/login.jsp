<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="loginpage.css">
</head>
<body>
<form action="login" method="post">
    Name:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="userpass"/><br/><br/>
    <input type="submit" value="sign in"/>
    <p style="color:red;">${errorMessage}</p>
    <input type="button" value="sign up" onClick='location.href="registration.html"'>
</form>
</body>
</html>