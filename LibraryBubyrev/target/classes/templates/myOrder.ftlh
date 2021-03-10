<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.pageWeb>

    <div class="form-group mt-3">
        <div>
            <form method = "post">
                <h5 class="m-4">Заказать книгу</h5>

                <input type="text" name="bookId" class="form-control ${(bookIdError??)?string('is-invalid', '')}" value="<#if bookId??>${bookId}</#if>" placeholder="Введите id книги"/>

                <#if bookIdError??>
                    <div class="invalid-feedback">
                        ${bookIdError}
                    </div>
                </#if>

                <#if bookAmountError??>
                    <div class="invalid-feedback">
                        ${bookAmountError}
                    </div>
                </#if>


                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary m-4">Заказать книгу из хранилища</button>
            </form>
        </div>
    </div>

    <#include "parts/orderListMyOrdert.ftlh">

</@c.pageWeb>