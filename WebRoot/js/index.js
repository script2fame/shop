/**
 * 
 */
function search_product(){
		var productType = $('#inp_search_product').val();
		console.log(productType);
		window.location.href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=1&productType="+productType;
}