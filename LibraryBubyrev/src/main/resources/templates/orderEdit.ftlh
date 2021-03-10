<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/bookInf.ftlh" as b>
<#import "parts/userInf.ftlh" as u>

<@c.pageWeb>


    <h5>Читатель:</h5>
<@u.userInf true/>


    <h5>Книга:</h5>

    <@b.bookInf true/>

<h5>Изменить данные о заказе</h5>

<div>
    <div class="m-4">
        <div class="row">
            <div class="col-sm">

                <form action="/orderList/${order.id}" method="post">
                    <div class="mb-4" >Статус книги :</div>
                   <div>
                        <p><input name="orderStatus" type="radio" value="SENT" ${order.roles?seq_contains("SENT")?string("checked","")} > Отправлен </p>
                        <p><input name="orderStatus" type="radio" value="ISSUED" ${order.roles?seq_contains("ISSUED")?string("checked","")} > Выдан </p>
                        <p><input name="orderStatus" type="radio" value="EXPIRED" ${order.roles?seq_contains("EXPIRED")?string("checked","")} > Просрочен </p>
                        <p><input name="orderStatus" type="radio" value="RETURNED" ${order.roles?seq_contains("RETURNED")?string("checked","")} > Возвращен </p>
                    </div>

                    <input type="hidden" name="orderId" value="${order.id}" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button class="btn btn-primary m-4" type="submit" >Сохранить</button>
                    <a class="btn btn-danger m-4" href="/orderList/delete/${order.id}">Удалить заказ</a>
                </form>

            </div>
            <div class="col-sm">
                <div>Id заказа : ${order.id}</div>

                <div>Заказ отправлен в работу : ${order.startTime}</div>
                <div>Время до возвращения книги : ${order.stockTime}</div>
            </div>
        </div>
    </div>
</div>












</@c.pageWeb>-+
