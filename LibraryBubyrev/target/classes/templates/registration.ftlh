<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.pageWeb>
    <div>Страница регистрации</div>
    ${message?ifExists}
    <@l.login "/registration" true/>
    <a href="/login">Уже есть аккаунт</a>

</@c.pageWeb>
