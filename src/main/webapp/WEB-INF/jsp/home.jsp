<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
	<input type="text" id="searchTxt" required="required" placeholder="Search Comments"/>
	<button type="submit" id="searchBtn">Search</button><button type="submit" id="resetBtn">Reset</button><br>
	
	<table hidden="hidden" id="searchDataTable" border="1">
		<tbody id="searchDataTableBody">
			<tr>
				<th>Post Id</th>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Body</th>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript"
        src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript">
		function deleteComment(identifier){
			$.ajax({
				type: "GET",
				url: "delete?deleteIdentifier="+identifier,
				success: function(){
					$('#searchDataTable').hide();
					$('#searchTxt').val('');
				}
			});
		}
		$(document).ready(function(){
			$('#searchBtn').click(function(){
				if($('#searchTxt').val()==''){
					alert('Please enter something');
				}
				else{
					$.ajax({
						type: "GET",
						url: "search?searchIdentifier="+$('#searchTxt').val(),
						dataType: "json",
						success: function(data){
							$('#searchDataTableBody').html('');
							var header = "<tr><th>Post Id</th><th>Id</th><th>Name</th><th>Email</th><th>Body</th><th>Option</th></tr>";
							$('#searchDataTableBody').append(header);
							if(data.length>0){
								var searchData = '';
								$.each(data, function(key,value){
									var deleteButton = "<button type='button' onclick='deleteComment("+value.id+")'>Delete</button>";
									searchData += '<tr>';
									searchData += '<td>'+value.postId+'</td>';
									searchData += '<td>'+value.id+'</td>';
									searchData += '<td>'+value.name+'</td>';
									searchData += '<td>'+value.email+'</td>';
									searchData += '<td>'+value.body+'</td>';
									searchData += '<td>'+deleteButton+'</td>';
									searchData += '</tr>';
								});
								$('#searchDataTableBody').append(searchData);
							}
							else{
								var row = "<tr><td colspan='6'>No data found</td></tr>";
								$('#searchDataTableBody').append(row);
							}
							$('#searchDataTable').show();
						},
						error:function(error){
							alert("Error: ", error);
						}
					});
				}
				
			});
			$('#resetBtn').click(function(){
				$.ajax({
					type: "GET",
					url: "/",
					success: function(){
						alert('Data Reset done!!!');
						$('#searchDataTable').hide();
						$('#searchTxt').val('');
					}
				});
			});
		});
	</script>
</body>
</html>