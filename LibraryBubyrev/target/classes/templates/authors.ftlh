<#import "parts/common.ftlh" as c>
<#import "parts/authorEdit.ftlh" as e>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.pageWeb>

    <#if isLibrarian>
    <a class="btn btn-primary ml-4" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        Добавить нового автора
    </a>
    <div class="collapse <#if author??>show</#if> ml-4 mb-4" id="collapseExample">
        <div class="form-group mt-3">

            <@e.authorEdit false/>

        </div>
    </div>
    </#if>

    <div class="form-row m-2">
        <div class="form-group col-md-8 col-lg-8 col-xl-8">
            <form method = "get" action="/authors" class="form">
                <input type="text" class="form-control m-2" name="filterTag" value="<#if myOptionalVar??>${filterTag}<#else></#if>" placeholder="Тэг для поиска автора"/>
                <input type="text" class="form-control m-2" name="filterAuthorLastName" value="<#if myOptionalVar??>${filterAuthorLastName}<#else></#if>" placeholder="Фамилия для поиска автора"/>
                <button type="submit" class="btn btn-primary ml-2" >Найти автора</button>
            </form>
        </div>
    </div>

    <@p.pager url page />
        <div class="card-columns">
            <#list page.content as author>
            <div class="card my-3">
                <a href="/authors/${author.id}">
                    <div>
                    <b>${author.id}</b>
                    <span>${author.firstName}</span>
                    <span>${author.lastName}</span>
                    <span>${author.middleName}</span>
                    </div>
                    <div>
                        <#if author.filename??>
                            <img src="/img/${author.filename}" class="card-img-top borderElement" />
                        </#if>
                    </div>
                    <i>${author.tag}</i>
                </a>
            </div>
            </#list>
        </div>
    <@p.pager url page />

</@c.pageWeb>