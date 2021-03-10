<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">LibraryBubyrev</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/books">Список книг</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/authors">Список авторов</a>
            </li>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/myOrder">Сделать заказ</a>
                </li>

            </#if>


            <#if isLibrarian>
                <li class="nav-item">
                   <a class="nav-link" href="/orderList">Список заказов</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>



        </ul>
        <div class="nav-text mr-3">
            <#if user??>
                <a class="nav-link" href="/user/profile">
                    ${name}
                </a>
            <#else>

            </#if>
        </div>
        <@l.logout />


    </div>
</nav>