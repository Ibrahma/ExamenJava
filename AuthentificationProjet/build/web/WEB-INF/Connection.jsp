<%@ page language="java" %>
<%@page import="com.java.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="us" class="com.java.user" scope="session"/>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Accueil</title>
        <script language="javascript" src="js/jquery-1.11.2.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/style.css" rel="stylesheet" />
        <script src="js/scripts.js" ></script>

    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Ajout <b>Utlisateur</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <input type="hidden" name="hdnbt" />
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Ajout Utilisateur</span></a>
                        </div>
                    </div>
                </div>
                <table id="monTableau" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Email</th>
                            <th>Adresse</th>
                            <th>Telephone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${users}">
                            <tr>
                                <td ><c:out value="${u.id}" /></td>
                                <td><c:out value="${u.nom}" /></td>
                                <td><c:out value="${u.prenom}" /></td>
                                <td><c:out value="${u.email}" /></td>
                                <td><c:out value="${u.adresse}" /></td>
                                <td>
                                    <c:out value="${u.telephone}" />                   
                                </td>
                                <td>
                                    <input type="hidden" name="hdnbt" />
                                    <button type="button" class="btn btn-info"  data-dismiss="modal" href="#editEmployeeModal"  data-toggle="modal">Edit</button></br>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" href="#deleteEmployeeModal"  data-toggle="modal">Supprimer</button></br>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Ajouter un utilisateur -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form  method="POST" Action="http://localhost:8080/AuthentificationProjetOuz/Register" >
                        <div class="modal-header">						
                            <h4 class="modal-title">Ajout Utilisateur</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Nom</label>
                                <input name="nom" type="text" class="form-control" required >
                            </div>
                            <div class="form-group">
                                <label>Prenom</label>
                                <input name="prenom" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email*</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Adresse</label>
                                <input name="adresse" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Telephone</label>
                                <input name="telephone" type="text" class="form-control" required>
                            </div>					
                            <div class="form-group">
                                <label>Password</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>					
                            <div class="form-group">
                                <label>Confirmation Password</label>
                                <input name="cpassword" type="password" class="form-control" required>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="hdnbt" />
                            <input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <!--<input type="submit" name="register" class="btn btn-success" value="Add">-->
                            <button name="register" value="register" class="btn btn-success" id="RegisterMessageButton" type="submit" onclick="{
                                        document.frm.hdnbt.value = this.value;
                                        document.frm.submit();
                                    }">Ajout</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" Action="http://localhost:8080/AuthentificationProjetOuz/Edit">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Utilisateur</h4>
                            <div class="modal-body">					
                                <p >Verifier l'identifiant (id) que vous mettez ?</p>
                                <p class="text-warning"><small>Une fausse id implique qu'il y'aura pas modification.</small></p>
                            </div>

                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Id</label>
                                <input name="id" type="text" class="form-control" required >
                            </div>
                           <div class="form-group">
                                <label>Nom</label>
                                <input name="nom" type="text" class="form-control" required >
                            </div>
                            <div class="form-group">
                                <label>Prenom</label>
                                <input name="prenom" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email*</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Adresse</label>
                                <input name="adresse" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Telephone</label>
                                <input name="telephone" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Confirmation Password</label>
                                <input name="cpassword" type="password" class="form-control" required>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <button type="submit" name="edit" class="btn btn-info" value="edit">Sauve</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" Action="http://localhost:8080/AuthentificationProjetOuz/Delete" >
                        <div class="modal-header">						
                            <h4 class="modal-title">Supprimer Utilisateur </ </h4>

                            <div class="form-group">
                                <label>Id</label>
                                <input name="id" type="text" class="form-control" placeholder="Id Utilisateur à supprimer*" required>
                            </div>
                        </div>
                        <div class="modal-body">					
                            <p >Etes sure de vouloir supprimer l'utilisateur ?</p>
                            <p class="text-warning"><small>Cette action ne peut pas être annulée.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="hdnbt" />
                            <button type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">Cancel</button>
                            <button name="delete" type="submit" class="btn btn-danger" value="delete">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>                                		                            