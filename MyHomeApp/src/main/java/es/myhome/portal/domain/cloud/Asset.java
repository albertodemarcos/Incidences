package es.myhome.portal.domain.cloud;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class Asset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4298778600215491489L;
	
	private String name;
	private String key;
	private URL url;

	public Asset() {
		super();
	}

	public Asset(String name, String key, URL url) {
		super();
		this.name = name;
		this.key = key;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, name, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		return Objects.equals(key, other.key) && Objects.equals(name, other.name) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Asset [name=" + name + ", key=" + key + ", url=" + url + ", getName()=" + getName() + ", getKey()="
				+ getKey() + ", getUrl()=" + getUrl() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

}
