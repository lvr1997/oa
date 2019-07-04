<#-- 参数说明：pageNo为当前页码，totalPage总页数，pageSize每页显示的条数，pageUrl分页请求URL，
              pageStyle分页样式，maxShowNums显示最多分页个数 -->
<#macro page pageNo totalPage pageSize pageUrl pageStyle maxShowNums=10>
    <#-- 本段默认起始结束页 -->
    <#local pageStartNo = 1/>
    <#-- 如果总页数小于10页 maxShowNums = totalPage -->
    <#if totalPage lt 10>
        <#local maxShowNums = totalPage/>
    </#if>
    <#local pageEndNo = maxShowNums/>
    <#-- 分页的起始结束页 -->
    <#local firstPageNo = 1/>
    <#local lastPageNo = totalPage/>
    <#-- 前后页 -->
    <#local prePageNo = pageNo-1/>
    <#local nextPageNo = pageNo+1/>

    <#if pageNo == pageStartNo>
        <#local prePageNo = pageStartNo/>
    </#if>
    <#if pageNo == lastPageNo>
        <#local nextPageNo = lastPageNo />
    </#if>

    <#if pageNo gt maxShowNums>
        <#local thisPageSegment = ((pageNo-1) / maxShowNums)?int + 1/>
        <#assign thisPageStartNo = pageStartNo + (thisPageSegment-1) * maxShowNums/>
        <#assign thisPageEndNo = pageEndNo + (thisPageSegment-1) * maxShowNums/>
        <#if thisPageEndNo gt totalPage>
            <#assign thisPageEndNo = totalPage>
        </#if>
    <#else>
        <#assign thisPageStartNo = pageStartNo />
        <#assign thisPageEndNo = pageEndNo />
    </#if>

    <#-- 构造当前分页栏上面的分页按钮 -->
    <#local pages=[] />
    <#if totalPage != 0>
        <#list thisPageStartNo .. thisPageEndNo as index>
            <#if pageNo == index >
                <#local pages = pages + [{"pageNum" : index ,"url" : pageUrl+"("+index+")" , "current" : true}] />
            <#else>
                <#local pages = pages + [{"pageNum" : index ,"url" : pageUrl+"("+index+")" , "current" : false}] />
            </#if>
        </#list>
    </#if>

    <#-- 构造分页栏HTML代码 -->
    <#local htmlNoLinkLine>
        <a href = "javascript:${pageUrl+'('+1+')'};" target = "_self">首页</a>
        <#if pageNo != pageStartNo>
            <a href = "javascript:${pageUrl+'('+prePageNo+')'};" target = "_self">上一页</a>
        </#if>

        <#list pages as page>
            <#if page.current?default(false)>
                <span class="current" >${page.pageNum}</span>
            <#else>
                <a href="javascript:${page.url};" mce_href="javascript:${page.url};" target="_self">${page.pageNum}</a>
            </#if>
        </#list>

        <#if pageNo != lastPageNo>
            <a href = "javascript:${pageUrl+'('+nextPageNo+')'};" target = "_self">下一页</a>
        </#if>
        <a href = "javascript:${pageUrl+'('+lastPageNo+')'};" target = "_self">末页</a>
        <span>共${totalPage?default(0)}页</span>
    </#local>

    <!-- 将分页HTML代码放置到页面 -->
    <div class="${pageStyle}">
        ${htmlNoLinkLine}
    </div>

    <!-- 分页插件CSS -->
    <style type="text/css" mce_bogus="1">
            <#if pageStyle == "gray">
            <#-- CSS gray style pagination -->
                div.gray {
                    font-size: 12px;
                    font-family: verdana, arial, helvetica, sans-serif;
                    padding: 3px;
                    margin: 3px;
                    text-align: center;
                    color:#999999;
                }
                div.gray a {
                    border: #E1E2E3 1px solid;
                    padding: 7px 14px;
                    margin: 2px;
                    color: #0000CC;
                    text-decoration: none;
                }
                div.gray a:hover {
                    border: #3388FF 1px solid;
                    background: #F2F8FF;
                }
                div.gray a:active {
                    color: #FF0000;
                }
                div.gray span.current {
                    border: transparent 1px solid;
                    font-weight: bold;
                    margin: 2px;
                    padding: 7px 14px;
                    color: #333333;
                }
            </#if>
            <#if pageStyle == "orange">
                <#-- CSS orange style pagination -->
                div.orange {
                    font-size: 12px;
                    font-family: verdana, arial, helvetica, sans-serif;
                    padding: 3px;
                    margin: 3px;
                    text-align: center;
                    color:#999999;
                }
                div.orange a {
                    border: #DFDFDF 1px solid;
                    padding: 7px 14px;
                    margin: 2px;
                    color: #3D3D3D;
                    text-decoration: none;
                }
                div.orange a:hover {
                    border: #FE4500 1px solid;
                    background: transparent;
                    color:#FE4500;
                }
                div.orange a:active {
                    color: #FE4500;
                }
                div.orange span.current {
                    border: #FF4400 1px solid;
                    background: #FF4400;
                    font-weight: bold;
                    margin: 2px;
                    padding: 7px 14px;
                    color: #FFFFFF;
                }
            </#if>
            <#if pageStyle == "blue">
                <#-- CSS blue style pagination -->
                div.blue {
                    font-size: 12px;
                    font-family: verdana, arial, helvetica, sans-serif;
                    padding: 3px;
                    margin: 3px;
                    text-align: center;
                    color:#999999;
                }
                div.blue a {
                    border: #E1E2E3 1px solid;
                    padding: 7px 14px;
                    margin: 2px;
                    color: #808080;
                    text-decoration: none;
                }
                div.blue a:hover {
                    border: #389CFF 1px solid;
                    background: transparent;
                    color:#389CFF;
                }
                div.blue a:active {
                    color: #389CFF;
                }
                div.blue span.current {
                    border: #389CFF 1px solid;
                    background: #389CFF;
                    font-weight: bold;
                    margin: 2px;
                    padding: 7px 14px;
                    color: #FFFFFF;
                }
            </#if>
    </style>
</#macro>