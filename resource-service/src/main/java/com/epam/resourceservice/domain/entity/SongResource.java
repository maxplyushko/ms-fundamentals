package com.epam.resourceservice.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song_resources")
public class SongResource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column(name = "location_path")
  private String locationPath;

  @Column(name = "upload_successful")
  private Boolean isUploadSuccessFul;

  @Column(name = "upload_date")
  private LocalDateTime uploadDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SongResource songResource = (SongResource) o;

    if (id.equals(songResource.id)) {
      return false;
    }
    if (name != null ? !name.equals(songResource.name) : songResource.name != null) {
      return false;
    }
    if (locationPath != null ? !locationPath.equals(songResource.locationPath)
        : songResource.locationPath != null) {
      return false;
    }
    if (isUploadSuccessFul != null ? !isUploadSuccessFul.equals(songResource.isUploadSuccessFul)
        : songResource.isUploadSuccessFul != null) {
      return false;
    }
    return uploadDate != null ? uploadDate.equals(songResource.uploadDate)
        : songResource.uploadDate == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (locationPath != null ? locationPath.hashCode() : 0);
    result = 31 * result + (isUploadSuccessFul != null ? isUploadSuccessFul.hashCode() : 0);
    result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
    return result;
  }
}
