<#include "security.ftlh">
<#import "login.ftlh" as l>

<#macro userInf isOrderForm>
<div>
    <div class="m-4">
        <div class="row">
            <div class="col-sm">
                <#if userCurrent.filename??>
                    <img src="/img/${userCurrent.filename}" height="200" class="borderElement" />
                </#if>
            </div>
            <div class="col-sm">
                <#if isOrderForm>
                    <h5 name="username"><a href="/user/${userCurrent.id}">${userCurrent.username} </a></h5>
                <#else>
                    <h5 name="username">${userCurrent.username}</h5>
                </#if>

                <#if isOrderForm>
                    <h5 name="username"><a href="/user/${userCurrent.id}"> ${userCurrent.firstName?ifExists} ${userCurrent.middleName?ifExists} ${userCurrent.lastName?ifExists} </a></h5>
                <#else>
                    <h5 name="username"> ${userCurrent.firstName?ifExists} ${userCurrent.middleName?ifExists} ${userCurrent.lastName?ifExists} </h5>
                </#if>

                ${message?ifExists}
                <div>Электронная почта : ${userCurrent.email!''}</div>
                <div>Телефон : ${userCurrent.phone!''}</div>
            </div>
        </div>
    </div>
</div>
</#macro>