<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "parts/authorEdit.ftlh" as e>
<#include "parts/security.ftlh">


<@c.pageWeb>

    <#include "parts/authorInf.ftlh">

    <#if isLibrarian>
    <div>
        <h5>Изменить информацию об авторе</h5>
                <@e.authorEdit true/>
    </div>
    </#if>


    <h5>Книги автора:</h5>


    <div class="card-columns">
        <#list books as book>
            <div class="card my-3">
                <a href="/books/${book.id}">
                    <b>${book.id}</b>
                    <span>${book.name}</span>
                    <div>${book.authorName}</div>

                    <div>
                        <#if book.filename??>
                            <img src="/img/${book.filename}" class="card-img-top borderElement" />
                        </#if>
                    </div>
                    <span>${book.year}</span>
                    <span>${book.country}</span>
                    <i>${book.tag}</i>
                </a>
            </div>
        </#list>
    </div>



</@c.pageWeb>