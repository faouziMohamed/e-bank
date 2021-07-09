<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:out value="${!empty client? client.name:'User'}'s  Dashboard | Online Bank"/>
    </title>
</head>
<body>

<h1>
    Good
    <c:out value="${5 <= hour && hour < 12? 'Morning': 'Evening'}"/>
    <c:out value="${client.name}"/>
</h1>

<p>Available operations</p>
<ul class="main-op">
    <li><a href="#" class="operation-link">Currents accounts</a></li>
    <li><a href="#" class="operation-link">Savings accounts</a></li>
    <li><a href="#" class="operation-link">make a transfer</a></li>
    <li><a href="#" class="operation-link">Logout</a></li>
</ul>
</body>
</html>
