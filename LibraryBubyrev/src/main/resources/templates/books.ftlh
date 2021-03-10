<#import "parts/common.ftlh" as c>
<#import "parts/bookEdit.ftlh" as e>
<#include "parts/security.ftlh">
<#import "parts/bookInf.ftlh" as b>
<#import "parts/pager.ftlh" as p>

<@c.pageWeb>


    <#if isLibrarian>
        <a class="btn btn-primary ml-4 " data-toggle="collapse" href="#collapseExample"  aria-expanded="false" aria-controls="collapseExample">
            Добавить новую книгу
        </a>
        <div class="collapse <#if book??>show</#if> ml-4 mb-4" id="collapseExample">
            <div class="form-group mt-3">
                 <div>
                     <@e.bookEdit false/>
                </div>
            </div>
        </div>
    </#if>


    <div class="form-row m-3">
        <form method = "get" action="/books" class="form-inline">
            <input type="text" class="form-control m-2" name="filterName" value="<#if myOptionalVar??>${filterName}<#else></#if>" placeholder="Введите название книги"/>
            <button type="submit" class="btn btn-primary ml-4">Найти по названию книги</button>
        </form>
    </div>


    <#if booksError??>
        <div class="invalid-feedback">
            ${booksError}
        </div>
    <#else >

    <@p.pager url page />
        <div class="card-columns">
            <#list page.content as book>
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
    <@p.pager url page />

    </#if>



</@c.pageWeb>