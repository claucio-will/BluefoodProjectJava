package com.claucio.bluefood.util;

import lombok.Getter;

@Getter
public enum FileType {

    PNG("image/png", "png"),
    JPG("image/jpeg", "jpg");

    //MimeType - Tipo tabelado que representa o tipo de arquivo
    final String mimeType;
    final String extension;

    //Construto com paramentros
    FileType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    //Comparar o mimetype passado por parametro é o mesmo que tem aqui no enum
    public boolean sameOf(String mimeType) {
        return this.mimeType.equalsIgnoreCase(mimeType);
    }

    public static FileType of(String mimeType){
      for (FileType fileType : values()){ // values retorna todos os elementos do enum
          if (fileType.sameOf(mimeType)){
              return fileType;
          }
      }
      return null;
    }
}
