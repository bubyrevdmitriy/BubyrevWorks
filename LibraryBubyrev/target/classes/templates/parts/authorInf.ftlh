<#include "security.ftlh">

<#import "login.ftlh" as l>

<div>
    <div class="m-4">
        <div class="row">
            <div class="col-sm">
                <#if author.filename??>
                    <img src="/img/${author.filename}" height="500" class="borderElement" />
                </#if>
            </div>
            <div class="col-sm">
                <h5>${author.firstName?ifExists} ${author.middleName?ifExists} ${author.lastName?ifExists}</h5>
                ${message?ifExists}
                <div>Id автора : ${author.id}</div>
                <div>Тэг : ${author.tag}</div>

                <div>Краткая информация:</div>
                <span>${author.info!''}</span>

            </div>
        </div>
    </div>
</div>

