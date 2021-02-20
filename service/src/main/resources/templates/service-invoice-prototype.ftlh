<html>

<head>
    <style media="screen, print" type="text/css">
        <#include "css/styles.css">
    </style>
    <p class="bold-all font-size-18">${invoice.issuer.firstName} ${invoice.issuer.lastName}</p>
    <p class="bold-all font-size-16">${invoice.title}</p>
</head>

<body>
<!-- basic invoice information section -->
<#include "sections/info-section.ftlh">
<!-- customer section -->
<p class="bold-all font-size-10">Στοιχεία Πελάτη</p>
<#include "sections/customer-section.ftlh">
<p>
<!-- details section -->
<#include "sections/details-section.ftlh">
<p>
<!-- amount section-->
<#include "sections/amount-section.ftlh">
<p>

<!-- supplier section -->
<#include "sections/supplier-section.ftlh">

</body>

<!-- footer section -->

</html>
