<#include "security.ftlh">
<#import "login.ftlh" as l>



<div>
    <h5>Список заказов</h5>


    <table class="table">
        <thead>
        <tr>
            <th scope="col">id заказа</th>
            <th scope="col">Название книги</th>

            <th scope="col">Статус заказа</th>
            <th scope="col">Время отправки заказа</th>
            <th scope="col">Время до возврата книги, мин</th>
            <th scope="col">Изменить</th>
        </tr>
        </thead>
        <tbody>
        <#list Orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.bookName}</td>

                <td>
                    ${order.roles?seq_contains("SENT")?string("Отправлен","")}
                    ${order.roles?seq_contains("ISSUED")?string("Выдан","")}
                    ${order.roles?seq_contains("EXPIRED")?string("Просрочен","")}
                    ${order.roles?seq_contains("RETURNED")?string("Возвращен","")}
                </td>
                <td>${order.startTime}</td>
                <td>${order.stockTime}</td>
                <td><a href="/orderList/${order.id}">изменить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>