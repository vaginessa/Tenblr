package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 16/11/16.
 */

public class Theme {

    @SerializedName("header_full_width")
    @Expose
    private Integer headerFullWidth;
    @SerializedName("header_full_height")
    @Expose
    private Integer headerFullHeight;
    @SerializedName("header_focus_width")
    @Expose
    private Integer headerFocusWidth;
    @SerializedName("header_focus_height")
    @Expose
    private Integer headerFocusHeight;
    @SerializedName("avatar_shape")
    @Expose
    private String avatarShape;
    @SerializedName("background_color")
    @Expose
    private String backgroundColor;
    @SerializedName("body_font")
    @Expose
    private String bodyFont;
    @SerializedName("header_bounds")
    @Expose
    private String headerBounds;
    @SerializedName("header_image")
    @Expose
    private String headerImage;
    @SerializedName("header_image_focused")
    @Expose
    private String headerImageFocused;
    @SerializedName("header_image_scaled")
    @Expose
    private String headerImageScaled;
    @SerializedName("header_stretch")
    @Expose
    private Boolean headerStretch;
    @SerializedName("link_color")
    @Expose
    private String linkColor;
    @SerializedName("show_avatar")
    @Expose
    private Boolean showAvatar;
    @SerializedName("show_description")
    @Expose
    private Boolean showDescription;
    @SerializedName("show_header_image")
    @Expose
    private Boolean showHeaderImage;
    @SerializedName("show_title")
    @Expose
    private Boolean showTitle;
    @SerializedName("title_color")
    @Expose
    private String titleColor;
    @SerializedName("title_font")
    @Expose
    private String titleFont;
    @SerializedName("title_font_weight")
    @Expose
    private String titleFontWeight;

    public Integer getHeaderFullWidth() {
        return headerFullWidth;
    }

    public Integer getHeaderFullHeight() {
        return headerFullHeight;
    }

    public Integer getHeaderFocusWidth() {
        return headerFocusWidth;
    }

    public Integer getHeaderFocusHeight() {
        return headerFocusHeight;
    }

    public String getAvatarShape() {
        return avatarShape;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBodyFont() {
        return bodyFont;
    }

    public String getHeaderBounds() {
        return headerBounds;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public String getHeaderImageFocused() {
        return headerImageFocused;
    }

    public String getHeaderImageScaled() {
        return headerImageScaled;
    }

    public Boolean getHeaderStretch() {
        return headerStretch;
    }

    public String getLinkColor() {
        return linkColor;
    }

    public Boolean getShowAvatar() {
        return showAvatar;
    }

    public Boolean getShowDescription() {
        return showDescription;
    }

    public Boolean getShowHeaderImage() {
        return showHeaderImage;
    }

    public Boolean getShowTitle() {
        return showTitle;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getTitleFont() {
        return titleFont;
    }

    public String getTitleFontWeight() {
        return titleFontWeight;
    }
}
