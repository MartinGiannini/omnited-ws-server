// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messageOut.proto

package coop.bancocredicoop.omnited.config;

import com.google.protobuf.GeneratedMessageV3;

public final class MessageOut {
  private MessageOut() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MensajeJSONOrBuilder extends
      // @@protoc_insertion_point(interface_extends:coop.bancocredicoop.omnited.config.MensajeJSON)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Un campo de tipo string con el identificador único
     * </pre>
     *
     * <code>string id = 1;</code>
     * @return The id.
     */
    java.lang.String getId();
    /**
     * <pre>
     * Un campo de tipo string con el identificador único
     * </pre>
     *
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    com.google.protobuf.ByteString
        getIdBytes();

    /**
     * <pre>
     * Tipo de mensaje
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The type.
     */
    java.lang.String getType();
    /**
     * <pre>
     * Tipo de mensaje
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The bytes for type.
     */
    com.google.protobuf.ByteString
        getTypeBytes();

    /**
     * <pre>
     * JSON serializado como string
     * </pre>
     *
     * <code>string jsonPayload = 3;</code>
     * @return The jsonPayload.
     */
    java.lang.String getJsonPayload();
    /**
     * <pre>
     * JSON serializado como string
     * </pre>
     *
     * <code>string jsonPayload = 3;</code>
     * @return The bytes for jsonPayload.
     */
    com.google.protobuf.ByteString
        getJsonPayloadBytes();

    /**
     * <pre>
     * Lista de destinatarios
     * </pre>
     *
     * <code>string recipients = 4;</code>
     * @return The recipients.
     */
    java.lang.String getRecipients();
    /**
     * <pre>
     * Lista de destinatarios
     * </pre>
     *
     * <code>string recipients = 4;</code>
     * @return The bytes for recipients.
     */
    com.google.protobuf.ByteString
        getRecipientsBytes();
  }
  /**
   * <pre>
   * Mensaje para encapsular JSON con múltiples destinatarios
   * </pre>
   *
   * Protobuf type {@code coop.bancocredicoop.omnited.config.MensajeJSON}
   */
  public static final class MensajeJSON extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:coop.bancocredicoop.omnited.config.MensajeJSON)
      MensajeJSONOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MensajeJSON.newBuilder() to construct.
    private MensajeJSON(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MensajeJSON() {
      id_ = "";
      type_ = "";
      jsonPayload_ = "";
      recipients_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new MensajeJSON();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return coop.bancocredicoop.omnited.config.MessageOut.internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return coop.bancocredicoop.omnited.config.MessageOut.internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.class, coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object id_ = "";
    /**
     * <pre>
     * Un campo de tipo string con el identificador único
     * </pre>
     *
     * <code>string id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Un campo de tipo string con el identificador único
     * </pre>
     *
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object type_ = "";
    /**
     * <pre>
     * Tipo de mensaje
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The type.
     */
    @java.lang.Override
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Tipo de mensaje
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The bytes for type.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int JSONPAYLOAD_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object jsonPayload_ = "";
    /**
     * <pre>
     * JSON serializado como string
     * </pre>
     *
     * <code>string jsonPayload = 3;</code>
     * @return The jsonPayload.
     */
    @java.lang.Override
    public java.lang.String getJsonPayload() {
      java.lang.Object ref = jsonPayload_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        jsonPayload_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * JSON serializado como string
     * </pre>
     *
     * <code>string jsonPayload = 3;</code>
     * @return The bytes for jsonPayload.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getJsonPayloadBytes() {
      java.lang.Object ref = jsonPayload_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        jsonPayload_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int RECIPIENTS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object recipients_ = "";
    /**
     * <pre>
     * Lista de destinatarios
     * </pre>
     *
     * <code>string recipients = 4;</code>
     * @return The recipients.
     */
    @java.lang.Override
    public java.lang.String getRecipients() {
      java.lang.Object ref = recipients_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        recipients_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Lista de destinatarios
     * </pre>
     *
     * <code>string recipients = 4;</code>
     * @return The bytes for recipients.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getRecipientsBytes() {
      java.lang.Object ref = recipients_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        recipients_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, type_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(jsonPayload_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, jsonPayload_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(recipients_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, recipients_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, type_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(jsonPayload_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, jsonPayload_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(recipients_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, recipients_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON)) {
        return super.equals(obj);
      }
      coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON other = (coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON) obj;

      if (!getId()
          .equals(other.getId())) return false;
      if (!getType()
          .equals(other.getType())) return false;
      if (!getJsonPayload()
          .equals(other.getJsonPayload())) return false;
      if (!getRecipients()
          .equals(other.getRecipients())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType().hashCode();
      hash = (37 * hash) + JSONPAYLOAD_FIELD_NUMBER;
      hash = (53 * hash) + getJsonPayload().hashCode();
      hash = (37 * hash) + RECIPIENTS_FIELD_NUMBER;
      hash = (53 * hash) + getRecipients().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * Mensaje para encapsular JSON con múltiples destinatarios
     * </pre>
     *
     * Protobuf type {@code coop.bancocredicoop.omnited.config.MensajeJSON}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:coop.bancocredicoop.omnited.config.MensajeJSON)
        coop.bancocredicoop.omnited.config.MessageOut.MensajeJSONOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return coop.bancocredicoop.omnited.config.MessageOut.internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return coop.bancocredicoop.omnited.config.MessageOut.internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.class, coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.Builder.class);
      }

      // Construct using coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        id_ = "";
        type_ = "";
        jsonPayload_ = "";
        recipients_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return coop.bancocredicoop.omnited.config.MessageOut.internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor;
      }

      @java.lang.Override
      public coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON getDefaultInstanceForType() {
        return coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.getDefaultInstance();
      }

      @java.lang.Override
      public coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON build() {
        coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON buildPartial() {
        coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON result = new coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.id_ = id_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.type_ = type_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.jsonPayload_ = jsonPayload_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.recipients_ = recipients_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON) {
          return mergeFrom((coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON other) {
        if (other == coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON.getDefaultInstance()) return this;
        if (!other.getId().isEmpty()) {
          id_ = other.id_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (!other.getType().isEmpty()) {
          type_ = other.type_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        if (!other.getJsonPayload().isEmpty()) {
          jsonPayload_ = other.jsonPayload_;
          bitField0_ |= 0x00000004;
          onChanged();
        }
        if (!other.getRecipients().isEmpty()) {
          recipients_ = other.recipients_;
          bitField0_ |= 0x00000008;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                id_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                type_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              case 26: {
                jsonPayload_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000004;
                break;
              } // case 26
              case 34: {
                recipients_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000008;
                break;
              } // case 34
              default: {
                  /*
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                  */
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object id_ = "";
      /**
       * <pre>
       * Un campo de tipo string con el identificador único
       * </pre>
       *
       * <code>string id = 1;</code>
       * @return The id.
       */
      public java.lang.String getId() {
        java.lang.Object ref = id_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Un campo de tipo string con el identificador único
       * </pre>
       *
       * <code>string id = 1;</code>
       * @return The bytes for id.
       */
      public com.google.protobuf.ByteString
          getIdBytes() {
        java.lang.Object ref = id_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          id_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Un campo de tipo string con el identificador único
       * </pre>
       *
       * <code>string id = 1;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        id_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Un campo de tipo string con el identificador único
       * </pre>
       *
       * <code>string id = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {
        id_ = getDefaultInstance().getId();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Un campo de tipo string con el identificador único
       * </pre>
       *
       * <code>string id = 1;</code>
       * @param value The bytes for id to set.
       * @return This builder for chaining.
       */
      public Builder setIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        id_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private java.lang.Object type_ = "";
      /**
       * <pre>
       * Tipo de mensaje
       * </pre>
       *
       * <code>string type = 2;</code>
       * @return The type.
       */
      public java.lang.String getType() {
        java.lang.Object ref = type_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          type_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Tipo de mensaje
       * </pre>
       *
       * <code>string type = 2;</code>
       * @return The bytes for type.
       */
      public com.google.protobuf.ByteString
          getTypeBytes() {
        java.lang.Object ref = type_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          type_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Tipo de mensaje
       * </pre>
       *
       * <code>string type = 2;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        type_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Tipo de mensaje
       * </pre>
       *
       * <code>string type = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        type_ = getDefaultInstance().getType();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Tipo de mensaje
       * </pre>
       *
       * <code>string type = 2;</code>
       * @param value The bytes for type to set.
       * @return This builder for chaining.
       */
      public Builder setTypeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        type_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }

      private java.lang.Object jsonPayload_ = "";
      /**
       * <pre>
       * JSON serializado como string
       * </pre>
       *
       * <code>string jsonPayload = 3;</code>
       * @return The jsonPayload.
       */
      public java.lang.String getJsonPayload() {
        java.lang.Object ref = jsonPayload_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          jsonPayload_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * JSON serializado como string
       * </pre>
       *
       * <code>string jsonPayload = 3;</code>
       * @return The bytes for jsonPayload.
       */
      public com.google.protobuf.ByteString
          getJsonPayloadBytes() {
        java.lang.Object ref = jsonPayload_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          jsonPayload_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * JSON serializado como string
       * </pre>
       *
       * <code>string jsonPayload = 3;</code>
       * @param value The jsonPayload to set.
       * @return This builder for chaining.
       */
      public Builder setJsonPayload(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        jsonPayload_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * JSON serializado como string
       * </pre>
       *
       * <code>string jsonPayload = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearJsonPayload() {
        jsonPayload_ = getDefaultInstance().getJsonPayload();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * JSON serializado como string
       * </pre>
       *
       * <code>string jsonPayload = 3;</code>
       * @param value The bytes for jsonPayload to set.
       * @return This builder for chaining.
       */
      public Builder setJsonPayloadBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        jsonPayload_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }

      private java.lang.Object recipients_ = "";
      /**
       * <pre>
       * Lista de destinatarios
       * </pre>
       *
       * <code>string recipients = 4;</code>
       * @return The recipients.
       */
      public java.lang.String getRecipients() {
        java.lang.Object ref = recipients_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          recipients_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Lista de destinatarios
       * </pre>
       *
       * <code>string recipients = 4;</code>
       * @return The bytes for recipients.
       */
      public com.google.protobuf.ByteString
          getRecipientsBytes() {
        java.lang.Object ref = recipients_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          recipients_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Lista de destinatarios
       * </pre>
       *
       * <code>string recipients = 4;</code>
       * @param value The recipients to set.
       * @return This builder for chaining.
       */
      public Builder setRecipients(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        recipients_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Lista de destinatarios
       * </pre>
       *
       * <code>string recipients = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearRecipients() {
        recipients_ = getDefaultInstance().getRecipients();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Lista de destinatarios
       * </pre>
       *
       * <code>string recipients = 4;</code>
       * @param value The bytes for recipients to set.
       * @return This builder for chaining.
       */
      public Builder setRecipientsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        recipients_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:coop.bancocredicoop.omnited.config.MensajeJSON)
    }

    // @@protoc_insertion_point(class_scope:coop.bancocredicoop.omnited.config.MensajeJSON)
    private static final coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON();
    }

    public static coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MensajeJSON>
        PARSER = new com.google.protobuf.AbstractParser<MensajeJSON>() {
      @java.lang.Override
      public MensajeJSON parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<MensajeJSON> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MensajeJSON> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020messageOut.proto\022\"coop.bancocredicoop." +
      "omnited.config\"P\n\013MensajeJSON\022\n\n\002id\030\001 \001(" +
      "\t\022\014\n\004type\030\002 \001(\t\022\023\n\013jsonPayload\030\003 \001(\t\022\022\n\n" +
      "recipients\030\004 \001(\tb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coop_bancocredicoop_omnited_config_MensajeJSON_descriptor,
        new java.lang.String[] { "Id", "Type", "JsonPayload", "Recipients", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
