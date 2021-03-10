<#include "security.ftlh">

<#macro bookEdit isEditForm>

<form method = "post" enctype="multipart/form-data">
    <div class="form-group">
        <#if isEditForm><label>Название книги:</label></#if>
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" name="name" placeholder="Введите название книги" value="<#if book??>${book.name}</#if>"/>
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Id автора книги:</label></#if>
        <input type="text" class="form-control ${(authorError??)?string('is-invalid', '')}" name="authorId" placeholder="Введите id автора" value="<#if book??>${book.authorId}</#if>"/>
        <#if authorError??>
            <div class="invalid-feedback">
                ${authorError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Год написания книги:</label></#if>
        <input type="text" class="form-control" name="year" placeholder="Введите год написания" value="<#if book??>${book.year!''}</#if>"/>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Страна написания книги:</label></#if>
        <input type="text" class="form-control" name="country" placeholder="Введите страну написания" value="<#if book??>${book.country!''}</#if>"/>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Жанр книги:</label></#if>
        <input type="text" class="form-control" name="tag" placeholder="Введите жанр" value="<#if book??>${book.tag!''}</#if>"/>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Обложка книги::</label></#if>
        <input type="file" class="form-control" name="file">
    </div>
    <div class="form-group">
        <#if isEditForm><label>Краткая информация о книге:</label></#if>
        <input type="text" class="form-control" name="info" placeholder="Введите крткую информацию о книге" value="<#if book??>${book.info!''}</#if>"/>
    </div>
    <div class="form-group">
         <#if isEditForm><label>Количество книг в библиотеке:</label></#if>
        <input type="number" class="form-control" name="bookAmount" placeholder="Введите количество книг в библиотеке" min="0" max="100"
               value="<#if isEditForm>${book.amountBooks!''}<#else>${amountBooks!''}</#if>">
    </div>


    <#if isEditForm>
        <div class="form-group">
            <label>Количество зарезервированных книг:</label>
            <input type="number" class="form-control" name="bookAmountReserved" placeholder="Введите количество зарезервированных книг" min="0" max="100"
                   value="${book.amountBooksReserved!''}">
        </div>
        <div class="form-group">
            <label>Количество книг на руках у пользователей:</label>
            <input type="number" class="form-control" name="bookAmountAtUsers" placeholder="Введите количество книг на руках у пользователей" min="0" max="100"
                   value="${book.amountBooksAtUsers!''}">
        </div>
    </#if>



    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary"><#if isEditForm>Изменить<#else>Добавить книгу</#if></button>

    <#if isEditForm>
        <a class="btn btn-danger m-4" href="/books/delete/${book.id}">Удалить книгу</a>
    </#if>

</form>


</#macro>

