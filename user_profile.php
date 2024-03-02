<?php
session_start();
if(isset($_SESSION['client_id'])){
  if($_SESSION['role']=='user'){
?>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<?php
require_once 'Database.php';
$user = $db->get_user($_SESSION['client_id']);
?>
<div class="row g-3 p-5">
<div class="col-md-6">
    <label for="inputEmail4" class="form-label">Name</label>
    <input type="text" class="form-control" id="inputName4"  value="<?=$user->get_name()?>" readonly>
  </div>
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">Phone Number</label>
    <input type="text" class="form-control" id="inputEmail4" readonly  value="<?=$user->get_phone_num()?>">
  </div>
  <div class="col-6">
    <label for="inputAddress" class="form-label">Address</label>
    <input type="text" class="form-control" id="inputAddress" readonly  value="<?=$user->get_address()?>">
  </div>
  <div class="col-6">
    <label for="inputAddress2" class="form-label">Birthday</label>
    <input type="text" class="form-control" id="inputAddress2" readonly  value="<?=$user->get_birthday()?>">
  </div>
  <div class="col-md-6">
    <label for="inputCity" class="form-label">Email</label>
    <input type="email" class="form-control" id="inputCity" readonly  value="<?=$user->get_email()?>">
  </div>
  <div class="col-12">
    <button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_">Change Password</button>
  </div>
  <!-- Modal -->
        <div class="modal fade" id="staticBackdrop_" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Change Password</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="ToChangePassword.php" method="post">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Old password</label>
                        <input type="text" class="form-control" name="old_password" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">New password</label>
                        <input type="text" class="form-control" name="new_password" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Confirm password</label>
                        <input type="text" class="form-control" name="confirm_password" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                    </div>
                    <input type="hidden" name="id" value="<?=$user->get_id()?>">
                    <button type="submit" class="btn btn-dark">Change</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
            </div>
        </div>
        </div>
        <!-- Modal -->
</div>
<?php }else{
    header("Location: profile.php");
      }
}else{
  header("Location: Login.php?first_log");
} ?>