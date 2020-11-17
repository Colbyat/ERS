package com.model;

public class Reimbursement {

	private int id;
	private String submitter;
	private float cost;
	private String type;
	private String imageURL;
	private String description;
	private String gradingFormat;
	private boolean isPending;
	private String resolvingManager;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int id, String submitter, float cost, String type, String imageURL, String description,
			String gradingFormat, boolean isPending, String resolvingManager) {
		super();
		this.id = id;
		this.submitter = submitter;
		this.cost = cost;
		this.type = type;
		this.imageURL = imageURL;
		this.description = description;
		this.gradingFormat = gradingFormat;
		this.isPending = isPending;
		this.resolvingManager = resolvingManager;
	}
	
	

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", submitter=" + submitter + ", cost=" + cost + ", type=" + type
				+ ", imageURL=" + imageURL + ", description=" + description + ", gradingFormat=" + gradingFormat
				+ ", isPending=" + isPending + ", resolvingManager=" + resolvingManager + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + (isPending ? 1231 : 1237);
		result = prime * result + ((resolvingManager == null) ? 0 : resolvingManager.hashCode());
		result = prime * result + ((submitter == null) ? 0 : submitter.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
			return false;
		if (id != other.id)
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (isPending != other.isPending)
			return false;
		if (resolvingManager == null) {
			if (other.resolvingManager != null)
				return false;
		} else if (!resolvingManager.equals(other.resolvingManager))
			return false;
		if (submitter == null) {
			if (other.submitter != null)
				return false;
		} else if (!submitter.equals(other.submitter))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public String getResolvingManager() {
		return resolvingManager;
	}

	public void setResolvingManager(String resolvingManager) {
		this.resolvingManager = resolvingManager;
	}
	
}
