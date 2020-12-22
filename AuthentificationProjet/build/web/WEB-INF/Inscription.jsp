<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <script src="https://use.fontawesome.com/releases/v5.12.1/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="js/scripts.js"></script>
    </head>
    <body id="page-top">
        <section class="page-section" id="contact">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Contact Abraham</h2>
                    <h3 class="section-subheading text-muted">Veuillez appeler le Mr</h3>
                </div>

                <form name="frm" method="POST" Action="http://localhost:8080/AuthentificationProjetOuz/Inscription" >
                    <div class="row align-items-stretch mb-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input class="form-control" name="nom"  id="nom" type="text" placeholder="Nom *"   />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="prenom"  id="prenom" type="text" placeholder="Prenom *"   />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="adresse"  id="adresse" type="text" placeholder="Adresse *"   />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="telephone"  id="telephone" type="text" placeholder="Telephone *"   />
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input class="form-control" name="email"  id="email" type="email" placeholder="Mail *"   />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="password"  id="password" type="password" placeholder="Mot de Passe*"  />

                            </div>
                            <div class="form-group">
                                <input class="form-control" name="cpassword"  id="cpassword" type="password" placeholder="Confirmation Passe*"  />

                            </div>


                        </div>
                    </div>
                    <div class="text-center" >
                        <div id="success"></div>
                       <button name="register" value="register" class="btn btn-secondary btn-xl text-uppercase" id="RegisterMessageButton" type="submit" onclick="{document.frm.hdnbt.value=this.value;document.frm.submit();}">Inscris</button>
                    </div>

                </form>
            </div>
        </section>


    </body>
</html>
