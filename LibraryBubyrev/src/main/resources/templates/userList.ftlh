<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.pageWeb>

    <div class="form-row m-3">
        <form method = "get" action="/user" class="form-inline">
            <input type="text" class="form-control m-2" name="userUsername" value="${userUsername!''}" placeholder="Введите имя пользователя в системе"/>
            <button type="submit" class="btn btn-primary ml-4">Найти по имени пользователя</button>
        </form>
    </div>



    <h5>Список пользователей</h5>

    <@p.pager url page />
    <table class="table">
        <thead>
        <tr>

            <th scope="col">Имя пользователя</th>
            <th scope="col">Роли</th>
            <th scope="col">Редактировать</th>
        </tr>
        </thead>
        <tbody>
            <#list page.content as user>
                <tr>
                    <td>${user.username}</td>
                    <td>
                        <#list user.roles as role>
                            ${role?contains("USER")?string("Читатель","")}
                            ${role?contains("LIBRARIAN")?string("Библиотекарь","")}
                            ${role?contains("ADMIN")?string("Администратор","")}
                            <#sep>,<br>
                        </#list>
                    </td>
                    <td><a href="/user/${user.id}">Подробнее</a></td>
                </tr>
            </#list>
        </tbody>
    </table>



</@c.pageWeb>