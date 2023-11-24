package com.photo.utility;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PhotoUtilityApp {

    public static void main(String[] args) {

        System.out.println("===== Welcome to Photo Utility Application ===2023===V1.0 ===");

        PhotoUtilityApp app = new PhotoUtilityApp();
        app.getFileInformation();

    }

    public void getFileInformation() {
        File myPhotoFile = new File(this.getClass().getClassLoader().getResource("IMG_20220811_144115.jpg").getFile());

        System.out.println("file exist : " + myPhotoFile.exists());
        System.out.println("fileName : " + myPhotoFile.getName());
        System.out.println("filePath : " + myPhotoFile.getPath());
        System.out.println("fileAbsolutePath : " + myPhotoFile.getAbsolutePath());
        System.out.println("fileSize : " + myPhotoFile.length());

        System.out.println("=================================================");

        try {
            metadataExample(myPhotoFile);
        } catch (ImagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void metadataExample(final File file) throws ImagingException,
            IOException {
        // get all metadata stored in EXIF format (ie. from JPEG or TIFF).
        final ImageMetadata metadata = Imaging.getMetadata(file);

        // System.out.println(metadata);

        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

            // Jpeg EXIF metadata is stored in a TIFF-based directory structure
            // and is identified with TIFF tags.
            // Here we look for the "x resolution" tag, but
            // we could just as easily search for any other tag.
            //
            // see the TiffConstants file for a list of TIFF tags.

            System.out.println("file: " + file.getPath());

            // print out various interesting EXIF tags.
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_XRESOLUTION);
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_DATE_TIME);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_ISO);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
            printTagValue(jpegMetadata,
                    GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            printTagValue(jpegMetadata,
                    GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE);

            System.out.println();

            // simple interface to GPS data
            final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
            if (null != exifMetadata) {
                final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                if (null != gpsInfo) {
                    final String gpsDescription = gpsInfo.toString();
                    final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                    final double latitude = gpsInfo.getLatitudeAsDegreesNorth();

                    System.out.println("    " + "GPS Description: "
                            + gpsDescription);
                    System.out.println("    "
                            + "GPS Longitude (Degrees East): " + longitude);
                    System.out.println("    "
                            + "GPS Latitude (Degrees North): " + latitude);
                }
            }

            // more specific example of how to manually access GPS values
            final TiffField gpsLatitudeRefField = jpegMetadata.findEXIFValueWithExactMatch(
                    GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
            final TiffField gpsLatitudeField = jpegMetadata.findEXIFValueWithExactMatch(
                    GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            final TiffField gpsLongitudeRefField = jpegMetadata.findEXIFValueWithExactMatch(
                    GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
            final TiffField gpsLongitudeField = jpegMetadata.findEXIFValueWithExactMatch(
                    GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
            if (gpsLatitudeRefField != null && gpsLatitudeField != null &&
                    gpsLongitudeRefField != null &&
                    gpsLongitudeField != null) {
                // all of these values are strings.
                final String gpsLatitudeRef = (String) gpsLatitudeRefField.getValue();
                final RationalNumber[] gpsLatitude = (RationalNumber[]) (gpsLatitudeField.getValue());
                final String gpsLongitudeRef = (String) gpsLongitudeRefField.getValue();
                final RationalNumber[] gpsLongitude = (RationalNumber[]) gpsLongitudeField.getValue();

                final RationalNumber gpsLatitudeDegrees = gpsLatitude[0];
                final RationalNumber gpsLatitudeMinutes = gpsLatitude[1];
                final RationalNumber gpsLatitudeSeconds = gpsLatitude[2];

                final RationalNumber gpsLongitudeDegrees = gpsLongitude[0];
                final RationalNumber gpsLongitudeMinutes = gpsLongitude[1];
                final RationalNumber gpsLongitudeSeconds = gpsLongitude[2];

                // This will format the gps info like so:
                //
                // gpsLatitude: 8 degrees, 40 minutes, 42.2 seconds S
                // gpsLongitude: 115 degrees, 26 minutes, 21.8 seconds E

                System.out.println("    " + "GPS Latitude: "
                        + gpsLatitudeDegrees.toDisplayString() + " degrees, "
                        + gpsLatitudeMinutes.toDisplayString() + " minutes, "
                        + gpsLatitudeSeconds.toDisplayString() + " seconds "
                        + gpsLatitudeRef);
                System.out.println("    " + "GPS Longitude: "
                        + gpsLongitudeDegrees.toDisplayString() + " degrees, "
                        + gpsLongitudeMinutes.toDisplayString() + " minutes, "
                        + gpsLongitudeSeconds.toDisplayString() + " seconds "
                        + gpsLongitudeRef);

            }

            System.out.println();

            final List<ImageMetadataItem> items = jpegMetadata.getItems();
            for (final ImageMetadataItem item : items) {
                System.out.println("    " + "item: " + item);
            }

            System.out.println();
        }
    }

    private void printTagValue(final JpegImageMetadata jpegMetadata,
                                      final TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
        if (field == null) {
            System.out.println(tagInfo.name + ": " + "Not Found.");
        } else {
            System.out.println(tagInfo.name + ": "
                    + field.getValueDescription());
        }
    }
}
