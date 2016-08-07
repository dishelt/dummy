<%-- 
    Document   : activity
    Created on : Aug 3, 2016, 12:11:21 AM
    Author     : dishelt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>    
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="js/locales/bootstrap-datetimepicker.en.js" charset="UTF-8"></script>
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-datetimepicker.css" rel="stylesheet" media="screen">
        <link href="css/style" rel="stylesheet" media="screen">
        <script>
            $(document).ready(function () {
                $(".modalUpdate").click(function () {
                    $('#myModal').find('input[name=description]').val($(this).data('description'));
                    $('#myModal').find('input[name=id]').val($(this).data('id'));
                    $('#myModal').find('input[name=deadline]').val($(this).data('deadline'));
                    $('#myModal').modal('show');
                });
            });
        </script>

        <title>Dummy Activities</title>
    </head>
    <body>
        <div class="container">
            <h1>Dummy Activities!</h1>
            <h2><b>Activities: ${listActivities.size()}</b></h2>
            <h3>Add Activity:</h3>
            <form id="addActivityForm" action = "/addActivity" method="post" modelAttribute="activityForm">
                <input type="hidden" path="id" name="id" value="900">
                <label>Description:</label>
                <br>
                <input type = "text" path="description" name = "description" id="description" data-validation="length" data-validation-length="min1">
                <br>
                <label>Date:   </label>
                <div class="input-append date form_datetime" data-date="2013-02-21T15:25:00Z">
                    <input size="20" type="text" value="" path="deadline" name = "deadline" id="deadline" readonly data-validation="length" data-validation-length="min1">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-calendar"></i></span>
                </div>
                <br>
                <input type = "submit" class="btn btn-primary" value = "Add">
            </form>
            <br>
            <table class="table table-hover">
                <tr class="info">
                    <th>ID</th>
                    <th>Description</th>
                    <th>Deadline</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${listActivities}" var="value" >
                    <jsp:useBean id="date" class="java.util.Date"/>
                    <c:set target="${date}" property="time" value="${value.deadline}"/> 
                    <fmt:formatDate value="${date}" type="date" pattern="yyyy/MM/dd" var ="formattedDate"/>
                    <tr>
                        <td class="active">${value.id}</td>
                        <td>${value.description}</td>
                        <td> ${formattedDate}</td>
                        <td><a class="btn btn-warning btn-xs modalUpdate" data-toggle="modal" data-id="${value.id}" data-description="${value.description}" data-deadline="${formattedDate}">Update</a></td>
                        <td><a href="/deleteActivity/${value.id}" class="btn btn-danger btn-xs">Delete</a></td>                        
                    </c:forEach>
                </tr>
            </table>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Update Activity</h4>
                        </div>
                        <div class="modal-body">
                            <form action = "/updateActivity" method="post" modelAttribute="updateForm">
                                <input type="hidden" path="id" name="id" value="1">
                                <label>Description: </label>
                                <br>
                                <input type = "text" path="description" name = "description" id = "description" value="" data-validation="length" data-validation-length="min1">
                                <br><br>
                                <label>Date:   </label>
                                <div class="input-append date form_datetime" data-date="2013-02-21T15:25:00Z">
                                    <input size="20" type="text" value="" path="deadline" name = "deadline" id="deadline" readonly value="" data-validation="length" data-validation-length="min1">
                                    <span class="add-on"><i class="icon-remove"></i></span>
                                    <span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                                <br>
                                <input type = "submit" class="btn btn-primary" value = "Update">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </body>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script>
            $.validate({
                lang: 'en'
            });
            $(".form_datetime").datetimepicker({
                format: "yyyy/mm/dd",
                autoclose: true,
                todayBtn: true,
                startDate: "2016-01-01",
                minuteStep: 60
            });
    </script> 
</html>
