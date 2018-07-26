<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page session="false" %>

<t:layout>
    <div class="container">

        <h1 class="my-4  text-center" >Setting Vulnerability</h1>


            <div>
                <form  method="POST" action="/settingVal">
                	<div class="form-group">
                        <label for="UseCookie"><strong>Password Storage</strong></label>
                        <select class="form-control" name="PwdStorage" id="PwdStorage" required>
                            <option value="Clear">Cleartext</option>
                            <option value="Hashed">SHA256</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="UseCookie"><strong>Cookie Type</strong></label>
                        <select class="form-control" name="UseCookie" id="UseCookie" required>
                            <option value="Base64">Base64</option>
                            <option value="Secure">SecureRandom</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="CookieParam"><strong>Cookie Param</strong></label>
                        <select class="form-control" name="CookieParam" id="CookieParam" required>
                            <option value="True">HttpOnly</option>
                            <option value="False">No HttpOnly</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="SessFix"><strong>Cookie Type</strong></label>
                        <select  class="form-control" name="SessFix" id="SessFix" required>
                            <option value="Yes">Yes</option>
                            <option value="No">No</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Sqli"><strong>Sql Injection</strong></label>
                        <select class="form-control" name="Sqli" id="Sqli" required>
                            <option value="1">Yes</option>
                            <option value="0">No</option>
                        </select>
                    </div>
                    <div class="form-group offset-md-5" >
                        <input class="btn btn-primary col-md-3"  type="submit" value="Submit">
                    </div>
                </form>
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
</t:layout>
