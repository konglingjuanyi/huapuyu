// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: response.proto

package com.anders.netty.protobuf.protos;

public final class ResponseProtos {
  private ResponseProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:netty.Response)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 id = 1;</code>
     *
     * <pre>
     *required int32 id = 1;
     * </pre>
     */
    boolean hasId();
    /**
     * <code>required int64 id = 1;</code>
     *
     * <pre>
     *required int32 id = 1;
     * </pre>
     */
    long getId();

    /**
     * <code>required bool ok = 2;</code>
     */
    boolean hasOk();
    /**
     * <code>required bool ok = 2;</code>
     */
    boolean getOk();

    /**
     * <code>required string desc = 3;</code>
     */
    boolean hasDesc();
    /**
     * <code>required string desc = 3;</code>
     */
    java.lang.String getDesc();
    /**
     * <code>required string desc = 3;</code>
     */
    com.google.protobuf.ByteString
        getDescBytes();
  }
  /**
   * Protobuf type {@code netty.Response}
   */
  public static final class Response extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:netty.Response)
      ResponseOrBuilder {
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Response(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Response defaultInstance;
    public static Response getDefaultInstance() {
      return defaultInstance;
    }

    public Response getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Response(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              id_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              ok_ = input.readBool();
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              desc_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.anders.netty.protobuf.protos.ResponseProtos.internal_static_netty_Response_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.anders.netty.protobuf.protos.ResponseProtos.internal_static_netty_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.anders.netty.protobuf.protos.ResponseProtos.Response.class, com.anders.netty.protobuf.protos.ResponseProtos.Response.Builder.class);
    }

    public static com.google.protobuf.Parser<Response> PARSER =
        new com.google.protobuf.AbstractParser<Response>() {
      public Response parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private long id_;
    /**
     * <code>required int64 id = 1;</code>
     *
     * <pre>
     *required int32 id = 1;
     * </pre>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 id = 1;</code>
     *
     * <pre>
     *required int32 id = 1;
     * </pre>
     */
    public long getId() {
      return id_;
    }

    public static final int OK_FIELD_NUMBER = 2;
    private boolean ok_;
    /**
     * <code>required bool ok = 2;</code>
     */
    public boolean hasOk() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required bool ok = 2;</code>
     */
    public boolean getOk() {
      return ok_;
    }

    public static final int DESC_FIELD_NUMBER = 3;
    private java.lang.Object desc_;
    /**
     * <code>required string desc = 3;</code>
     */
    public boolean hasDesc() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string desc = 3;</code>
     */
    public java.lang.String getDesc() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          desc_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string desc = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        desc_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      id_ = 0L;
      ok_ = false;
      desc_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasOk()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasDesc()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(2, ok_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getDescBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, ok_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getDescBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.anders.netty.protobuf.protos.ResponseProtos.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.anders.netty.protobuf.protos.ResponseProtos.Response prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code netty.Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:netty.Response)
        com.anders.netty.protobuf.protos.ResponseProtos.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.anders.netty.protobuf.protos.ResponseProtos.internal_static_netty_Response_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.anders.netty.protobuf.protos.ResponseProtos.internal_static_netty_Response_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.anders.netty.protobuf.protos.ResponseProtos.Response.class, com.anders.netty.protobuf.protos.ResponseProtos.Response.Builder.class);
      }

      // Construct using com.anders.netty.protobuf.protos.ResponseProtos.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        id_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        ok_ = false;
        bitField0_ = (bitField0_ & ~0x00000002);
        desc_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.anders.netty.protobuf.protos.ResponseProtos.internal_static_netty_Response_descriptor;
      }

      public com.anders.netty.protobuf.protos.ResponseProtos.Response getDefaultInstanceForType() {
        return com.anders.netty.protobuf.protos.ResponseProtos.Response.getDefaultInstance();
      }

      public com.anders.netty.protobuf.protos.ResponseProtos.Response build() {
        com.anders.netty.protobuf.protos.ResponseProtos.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.anders.netty.protobuf.protos.ResponseProtos.Response buildPartial() {
        com.anders.netty.protobuf.protos.ResponseProtos.Response result = new com.anders.netty.protobuf.protos.ResponseProtos.Response(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.ok_ = ok_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.desc_ = desc_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.anders.netty.protobuf.protos.ResponseProtos.Response) {
          return mergeFrom((com.anders.netty.protobuf.protos.ResponseProtos.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.anders.netty.protobuf.protos.ResponseProtos.Response other) {
        if (other == com.anders.netty.protobuf.protos.ResponseProtos.Response.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasOk()) {
          setOk(other.getOk());
        }
        if (other.hasDesc()) {
          bitField0_ |= 0x00000004;
          desc_ = other.desc_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        if (!hasOk()) {
          
          return false;
        }
        if (!hasDesc()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.anders.netty.protobuf.protos.ResponseProtos.Response parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.anders.netty.protobuf.protos.ResponseProtos.Response) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long id_ ;
      /**
       * <code>required int64 id = 1;</code>
       *
       * <pre>
       *required int32 id = 1;
       * </pre>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 id = 1;</code>
       *
       * <pre>
       *required int32 id = 1;
       * </pre>
       */
      public long getId() {
        return id_;
      }
      /**
       * <code>required int64 id = 1;</code>
       *
       * <pre>
       *required int32 id = 1;
       * </pre>
       */
      public Builder setId(long value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 id = 1;</code>
       *
       * <pre>
       *required int32 id = 1;
       * </pre>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0L;
        onChanged();
        return this;
      }

      private boolean ok_ ;
      /**
       * <code>required bool ok = 2;</code>
       */
      public boolean hasOk() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required bool ok = 2;</code>
       */
      public boolean getOk() {
        return ok_;
      }
      /**
       * <code>required bool ok = 2;</code>
       */
      public Builder setOk(boolean value) {
        bitField0_ |= 0x00000002;
        ok_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool ok = 2;</code>
       */
      public Builder clearOk() {
        bitField0_ = (bitField0_ & ~0x00000002);
        ok_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object desc_ = "";
      /**
       * <code>required string desc = 3;</code>
       */
      public boolean hasDesc() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string desc = 3;</code>
       */
      public java.lang.String getDesc() {
        java.lang.Object ref = desc_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            desc_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string desc = 3;</code>
       */
      public com.google.protobuf.ByteString
          getDescBytes() {
        java.lang.Object ref = desc_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          desc_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string desc = 3;</code>
       */
      public Builder setDesc(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        desc_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string desc = 3;</code>
       */
      public Builder clearDesc() {
        bitField0_ = (bitField0_ & ~0x00000004);
        desc_ = getDefaultInstance().getDesc();
        onChanged();
        return this;
      }
      /**
       * <code>required string desc = 3;</code>
       */
      public Builder setDescBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        desc_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:netty.Response)
    }

    static {
      defaultInstance = new Response(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:netty.Response)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_netty_Response_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_netty_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016response.proto\022\005netty\"0\n\010Response\022\n\n\002i" +
      "d\030\001 \002(\003\022\n\n\002ok\030\002 \002(\010\022\014\n\004desc\030\003 \002(\tB2\n com" +
      ".anders.netty.protobuf.protosB\016ResponseP" +
      "rotos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_netty_Response_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_netty_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_netty_Response_descriptor,
        new java.lang.String[] { "Id", "Ok", "Desc", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
