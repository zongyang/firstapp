package Model;

import CommFuns.JSON;

public class TipModel {
	private Boolean success;
	private String msg;
	private String actions;

	public TipModel(Boolean success, String msg, String actions) {

		this.success = success;
		this.msg = msg;
		this.actions = actions;
	}

	public String ToJSON() {
		return JSON.ObjToJSON(this);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}
}

