<#include "security.ftlh">

<#macro authorEdit isEditForm>

<form method = "post" enctype="multipart/form-data">
    <div class="form-group">
        <#if isEditForm><label>Имя автора:</label></#if>
        <input type="text" name="firstName" class="form-control ${(firstNameError??)?string('is-invalid', '')}" placeholder="Введите Имя" value="<#if author??>${author.firstName}</#if>"/>
        <#if firstNameError??>
            <div class="invalid-feedback">
                ${firstNameError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Отчество автора (или второе имя):</label></#if>
        <input type="text" name="middleName" class="form-control" placeholder="Введите Отчество" value="<#if author??>${author.middleName}</#if>"/>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Фамилия автора:</label></#if>
        <input type="text" name="lastName" class="form-control ${(lastNameError??)?string('is-invalid', '')}" placeholder="Введите Фамилию" value="<#if author??>${author.lastName}</#if>"/>
        <#if lastNameError??>
            <div class="invalid-feedback">
                ${lastNameError}
            </div>
        </#if>
    </div>

    <div class="form-group">
        <#if isEditForm><label>Тэг автра:</label></#if>
        <input type="text" name="tag" class="form-control" placeholder="Введите тэг" value="<#if author??>${author.tag}</#if>"/>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Портрет автора:</label></#if>
        <div class="form-group">
            <input type="file" class="form-control" name="file">
        </div>
    </div>
    <div class="form-group">
        <#if isEditForm><label>Краткая информация об автре:</label></#if>
        <input type="text" name="info" class="form-control" placeholder="Введите краткую информацию об авторе" value="<#if author??>${author.info!''}</#if>"/>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="form-group">
        <button type="submit" class="btn btn-primary"><#if isEditForm>Изменить<#else>Добавить Автора</#if></button>
        <#if isEditForm><a class="btn btn-danger m-4" href="/authors/delete/${author.id}">Удалить автора</a></#if>
    </div>
</form>
</#macro>
