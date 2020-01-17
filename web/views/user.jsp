<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Your Profile!</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <% if (request.getAttribute("userName") != null) {
                out.println("<h2>Hello, " + request.getAttribute("userName") + "!</h2 >");
            }
            %>
        </div>
        <p>Your visit: ${visit}</p>
        <p>Session creation date: ${creationTime}</p>
        <p>Last visit: ${lastVisitTime}</p>
        <p>Session ID: ${sessionId}</p>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='./'">Back to main</button>
</div>
</body>
</html>
