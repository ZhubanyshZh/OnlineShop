<!DOCTYPE html>
<html lang="en">
<head>
    <title>Index</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Signup.php">SignUp</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Login.php">Login</a>
        </li>
      <?php
      session_start();
      ?>
        <div class="input-group">
            <span class="input-group-text">Login and Password</span>
            <input type="text" aria-label="Login" class="form-control" value="<?=$_SESSION['client_name']?>">
            <input type="text" aria-label="Password" class="form-control" value="<?=$_SESSION['client_phone_num']?>">
        </div>
    </div>
  </div>
</nav>
</body>
</html>