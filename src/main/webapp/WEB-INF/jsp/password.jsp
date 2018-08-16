<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@ page session="false" %>

<t:layout>
    <div class="container">

        <h1 class="my-4"></h1>


        <!-- Features Section -->
        <div class="row">
  
                    <div class="col-lg-12">


                        <div class="col-lg-12 text-center">
                            <h2>User : ${userName} </h2>
                            <div class="clearfix"></div>
                            <hr>
                            <div>
                                <div class="col-lg-6 offset-3">
                                <label class="col-sm-4"><strong>Customer Name:</strong> </label>
                               <input  name="cusname" id="cusname" value="" class="hasDatepicker form-control">
                               <label class="col-sm-4"><strong>Customer Phone:</strong> </label>
                               <input  name="cusPhone" id="cusPhone" value="" class="hasDatepicker form-control">
                               <label class="col-sm-4"><strong>Salt:</strong> </label>
                               <input  name="salt" id="salt" value="" class="hasDatepicker form-control">
                               <p></p>
                              <button class="btn btn-primary btn-block" id="cat">Link (with credentials)</button>
                               <button class="btn btn-primary btn-block" id="dog">Link (without credentials)</button>
                               <p></p>
                               <p></p>
                                    <form action="/changePassword" method="POST">
                                        <div class="form-group col-md-12">
                                            <label for="password" class="col-sm-4"><strong>New password:</strong> </label>
                                            <input  type="password" name="password" id="password" value="" class="hasDatepicker form-control">
                                        </div>
                                         <div class="form-group col-md-12">
                                            <label for="password1" class="col-sm-4"><strong>Repeat:</strong> </label>
                                            <input  type="password" name="password1" id="password1" value="" class="hasDatepicker form-control">
                                        </div>
                                        <input type="hidden" name="_csrf" value="${_csrfToken}" />
                                        <div class="form-group col-md-12 " >
                                            <button class="btn btn-primary btn-block" type="submit" >Submit</button>
                                        </div>
                                    </form>
                                          	<c:if test="${not empty errorMessage}">
											<p style="color:red">${errorMessage}</p>
										</c:if>
                                </div>

                            </div>

                        </div>
                    </div>


        </div>
        <!-- /.row -->

        <hr>
		
	
		
        <!-- Call to Action Section -->
        <div class="row mb-4">
            <div class="col-md-12">
                <p>We work in IT Security Department of mgm Technology Partners. If you have any questions, please contact us:</p>
                <ul>
                    <li>Dennis.Stoetzel@mgm-sp.com-<em> Managing Principal IT Security Department</em> </li>
                    <li>Dentist.team@mgm-sp.com-<em> Member of IT Security Department</em> </li>
                </ul>
            </div>

        </div>
    </div>
   
<script type="text/javascript" >
$('#cat').click(function(){
	
	$.ajax({
		url: "https://api.flickr.com/services/rest/?&method=flickr.people.getPublicPhotos&format=json&api_key=6f93d9bd5fef5831ec592f0b527fdeff&user_id=9395899@N08",		
        type: "GET", 

        success: function(data){     
        	alert(1);
        	var obj = JSON.parse(JSON.stringify(data));
            $('#cusname').val(obj);	
            $('#cusPhone').val(obj);
        },
        error: function(xhr, status, error) {
            alert(23);
          }
});
});
</script>
</t:layout>
