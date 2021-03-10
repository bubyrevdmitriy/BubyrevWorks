<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "parts/bookEdit.ftlh" as e>
<#import "parts/bookInf.ftlh" as b>
<#include "parts/security.ftlh">

<@c.pageWeb>


    <@b.bookInf false/>
    <#if isLibrarian>
    <div>
        <h5>Изменить информацию о книге</h5>
                <@e.bookEdit true/>
    </div>
    </#if>

</@c.pageWeb>