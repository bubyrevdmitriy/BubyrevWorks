<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">
<#import "parts/userInf.ftlh" as u>

<@c.pageWeb>



    <@u.userInf false/>

    <#include "parts/userEdit.ftlh">


</@c.pageWeb>