<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- delete Modal -->
<div>
	<div class="modal fade modal-danger" id="del-modal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #ec3e29 !important; margin: 0px;">
					<h4 style="color: white; text-align: center; font-size: 24px">
						<b>CONFIRMATION</b>
					</h4>
				</div>

				<div
					style="padding-left: 10px; padding: 15px; background-color: white;; font-size: 20px; mrgin-left: 5px;">
					<input type="hidden" id="del-item-id">
					<p>Are you sure to delete this?</p>
				</div>
				<div class="modal-footer"
					style="background-color: white !important;">
					<button type="button" onclick="confirmDelelte()" id="del-item"
						class="btn btn-outline alert-success">Ok</button>
					<button type="button" class="btn btn-outline pull-left alert-info"
						data-dismiss="modal">Cancel</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
</div>
