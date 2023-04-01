package com.passwordmanager.domain.valueObjects;

import org.apache.commons.lang3.Validate;

public final class WebpageName {
    private String webpageName;

    public WebpageName() {
    }

    public WebpageName(String wbName) {
        Validate.notNull(wbName);
        int sz = wbName.length();
        Validate.inclusiveBetween(0, 100, sz, "The value must be between 0 and 100 characters");
        webpageName = wbName;
    }

    public String getWebsiteName() {
        return webpageName;
    }

    public void setWebpageName(String webpageName) {
        this.webpageName = webpageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WebpageName that = (WebpageName) o;
        return webpageName.equals(that.webpageName);
    }

    @Override
    public int hashCode() {
        return webpageName.hashCode();
    }

    @Override
    public String toString() {
        return webpageName;
    }
}
