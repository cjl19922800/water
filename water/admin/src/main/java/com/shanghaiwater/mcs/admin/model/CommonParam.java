package com.shanghaiwater.mcs.admin.model;

public class CommonParam {
	private String Action;
	private String Version;
	private String Timestamp;
	private String Nonce;

	private String AccessKeyId;
	private String Signature;
	private String SignatureMethod;
	private String SignatureVersion;

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

	public String getNonce() {
		return Nonce;
	}

	public void setNonce(String nonce) {
		Nonce = nonce;
	}

	public String getAccessKeyId() {
		return AccessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		AccessKeyId = accessKeyId;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getSignatureMethod() {
		return SignatureMethod;
	}

	public void setSignatureMethod(String signatureMethod) {
		SignatureMethod = signatureMethod;
	}

	public String getSignatureVersion() {
		return SignatureVersion;
	}

	public void setSignatureVersion(String signatureVersion) {
		SignatureVersion = signatureVersion;
	}

}
