/*
 * Copyright (c) 2010-2025 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.types;

import java.util.Date;

import com.restfb.Facebook;
import com.restfb.JsonMapper;
import com.restfb.JsonMapper.JsonMappingCompleted;
import com.restfb.types.features.HasComments;
import com.restfb.types.features.HasCreatedTime;
import com.restfb.types.features.HasProfilePicture;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the <a href="https://developers.facebook.com/docs/graph-api/reference/album/">Album Graph API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Album extends NamedFacebookType implements HasProfilePicture, HasComments, HasCreatedTime {

  /**
   * An object containing the ID and name of the profile who posted this album.
   * 
   * @return An object containing the ID and name of the profile who posted this album.
   */
  @Getter
  @Setter
  @Facebook
  private From from;

  /**
   * The description of the album.
   * 
   * @return The description of the album.
   */
  @Getter
  @Setter
  @Facebook
  private String description;

  /**
   * The location of the album.
   * 
   * @return The location of the album.
   */
  @Getter
  @Setter
  @Facebook
  private String location;

  /**
   * A link to this album on Facebook.
   * 
   * @return A link to this album on Facebook.
   */
  @Getter
  @Setter
  @Facebook
  private String link;

  /**
   * The number of photos in this album.
   * 
   * @return The number of photos in this album.
   */
  @Getter
  @Setter
  @Facebook
  private Long count;

  /**
   * The album cover photo ID.
   * 
   * @return The album cover photo ID
   */
  @Getter
  @Setter
  @Facebook("cover_photo")
  private Photo coverPhoto;

  @Facebook("cover_photo")
  private String coverPhotoAsString;

  /**
   * The privacy settings for the album.
   * 
   * @return The privacy settings for the album.
   */
  @Getter
  @Setter
  @Facebook
  private String privacy;

  /**
   * Whether or not the user has permission to upload to this album.
   * 
   * @return The {@code can_upload} setting for this album.
   */
  @Getter
  @Setter
  @Facebook("can_upload")
  private Boolean canUpload;

  /**
   * The time the photo album was initially created.
   * 
   * @return The time the photo album was initially created.
   */
  @Getter(onMethod_ = {@Override})
  @Setter
  @Facebook("created_time")
  private Date createdTime;

  /**
   * The last time the photo album was updated.
   * 
   * @return The last time the photo album was updated.
   */
  @Getter
  @Setter
  @Facebook("updated_time")
  private Date updatedTime;

  /**
   * The place associated with this album.
   *
   * @return The place associated with this album.
   */
  @Getter
  @Setter
  @Facebook
  private Place place;

  /**
   * The event associated with this album.
   *
   * @return The event associated with this album.
   */
  @Getter
  @Setter
  @Facebook
  private Event event;

  /**
   * The comments for this album.
   *
   * @return The comments for this album.
   */
  @Getter(onMethod_ = {@Override})
  @Setter
  @Facebook
  private Comments comments;

  @Facebook("picture")
  private transient String rawPicture;

  /**
   * The album's picture, if provided.
   *
   * To force Facebook to fill the <code>picture</code> field you have to fetch the album with the
   * <code>fields=picture</code> parameter, otherwise the picture is <code>null</code>.
   *
   * @return the album's picture as ProfilePictureSource object
   */
  @Getter(onMethod_ = {@Override})
  @Setter
  private ProfilePictureSource picture;

  /**
   * People who like this.
   *
   * @return The likes on this album.
   */
  @Getter
  @Setter
  @Facebook
  private Likes likes;

  private static final long serialVersionUID = 1L;

  @JsonMappingCompleted
  protected void fillPicture(JsonMapper jsonMapper) {
    picture = this.convertPicture(jsonMapper, rawPicture);
  }

  @JsonMappingCompleted
  private void fillCoverPhoto() {
    if (coverPhoto == null && coverPhotoAsString != null) {
      coverPhoto = new Photo();
      coverPhoto.setId(coverPhotoAsString);
    }
  }
}