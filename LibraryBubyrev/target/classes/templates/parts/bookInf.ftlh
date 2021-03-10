<#include "security.ftlh">

<#import "login.ftlh" as l>

<#macro bookInf isOrderForm>
<div>
    <div class="m-4">
        <div class="row">
            <div class="col-sm">
                <#if book.filename??>
                    <img src="/img/${book.filename!''}" height="<#if isOrderForm>200<#else>500</#if>" class="borderElement" />
                </#if>
            </div>
            <div class="col-sm">
                <#if isOrderForm><h5><a href="/books/${book.id}">${book.name!''} </a></h5><#else><h5>${book.name!''}</h5></#if>

                ${message?ifExists}
                <div>Id книги : ${book.id!''}</div>
                <div>
                    <span> Автор : <a href="/authors/${book.authorId}">${book.authorName!''} </a></span>
                </div>
                <div>Год написания : ${book.year!''}</div>
                <div>Страна написания/издания : ${book.country!''}</div>
                <div>Тэг : ${book.tag!''}</div>


                <div>Количество книг для заказа : ${book.amountBooks!'Книги временно отсутствуют'}</div>
                <#if isLibrarian>
                    <div>Количество книг зарезервированных : ${book.amountBooksReserved!''}</div>
                    <div>Количество выданных книг : ${book.amountBooksAtUsers!''}</div>
                </#if>


                    <div class="invalid-feedback">
                        ${bookAmountError!''}
                    </div>

                <#if !isOrderForm>
                    <div>Краткое описание :</div>
                    <div><span>${book.info!''}</span></div>
                    <a class="btn btn-primary m-4" href="/books/makeOrder/${book.id}">Заказать книгу</a>
                </#if>

            </div>
        </div>
    </div>
</div>
</#macro>