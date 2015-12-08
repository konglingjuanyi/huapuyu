/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.anders.netty.thrift.api;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "cast", "rawtypes", "serial", "unchecked" })
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-10-5")
public class Response
		implements org.apache.thrift.TBase<Response, Response._Fields>,
		java.io.Serializable, Cloneable, Comparable<Response> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct(
			"Response");

	private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"id", org.apache.thrift.protocol.TType.I32, (short) 1);
	private static final org.apache.thrift.protocol.TField OK_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"ok", org.apache.thrift.protocol.TType.BOOL, (short) 2);
	private static final org.apache.thrift.protocol.TField DESC_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"desc", org.apache.thrift.protocol.TType.STRING, (short) 3);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();

	static {
		schemes.put(StandardScheme.class, new ResponseStandardSchemeFactory());
		schemes.put(TupleScheme.class, new ResponseTupleSchemeFactory());
	}

	public int id; // required
	public boolean ok; // required
	public String desc; // required

	/**
	 * The set of fields this struct contains, along with convenience methods
	 * for finding and manipulating them.
	 */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		ID((short) 1, "id"), OK((short) 2, "ok"), DESC((short) 3, "desc");

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static {
			for (_Fields field : EnumSet.allOf(_Fields.class)) {
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its not
		 * found.
		 */
		public static _Fields findByThriftId(int fieldId) {
			switch (fieldId) {
			case 1: // ID
				return ID;
			case 2: // OK
				return OK;
			case 3: // DESC
				return DESC;
			default:
				return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an exception
		 * if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId) {
			_Fields fields = findByThriftId(fieldId);
			if (fields == null)
				throw new IllegalArgumentException(
						"Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not
		 * found.
		 */
		public static _Fields findByName(String name) {
			return byName.get(name);
		}

		private final short _thriftId;
		private final String _fieldName;

		_Fields(short thriftId, String fieldName) {
			_thriftId = thriftId;
			_fieldName = fieldName;
		}

		public short getThriftFieldId() {
			return _thriftId;
		}

		public String getFieldName() {
			return _fieldName;
		}
	}

	// isset id assignments
	private static final int __ID_ISSET_ID = 0;
	private static final int __OK_ISSET_ID = 1;
	private byte __isset_bitfield = 0;
	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

	static {
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(
				_Fields.class);
		tmpMap.put(_Fields.ID,
				new org.apache.thrift.meta_data.FieldMetaData("id",
						org.apache.thrift.TFieldRequirementType.DEFAULT,
						new org.apache.thrift.meta_data.FieldValueMetaData(
								org.apache.thrift.protocol.TType.I32)));
		tmpMap.put(_Fields.OK,
				new org.apache.thrift.meta_data.FieldMetaData("ok",
						org.apache.thrift.TFieldRequirementType.DEFAULT,
						new org.apache.thrift.meta_data.FieldValueMetaData(
								org.apache.thrift.protocol.TType.BOOL)));
		tmpMap.put(_Fields.DESC,
				new org.apache.thrift.meta_data.FieldMetaData("desc",
						org.apache.thrift.TFieldRequirementType.DEFAULT,
						new org.apache.thrift.meta_data.FieldValueMetaData(
								org.apache.thrift.protocol.TType.STRING)));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData
				.addStructMetaDataMap(Response.class, metaDataMap);
	}

	public Response() {
	}

	public Response(int id, boolean ok, String desc) {
		this();
		this.id = id;
		setIdIsSet(true);
		this.ok = ok;
		setOkIsSet(true);
		this.desc = desc;
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public Response(Response other) {
		__isset_bitfield = other.__isset_bitfield;
		this.id = other.id;
		this.ok = other.ok;
		if (other.isSetDesc()) {
			this.desc = other.desc;
		}
	}

	public Response deepCopy() {
		return new Response(this);
	}

	@Override
	public void clear() {
		setIdIsSet(false);
		this.id = 0;
		setOkIsSet(false);
		this.ok = false;
		this.desc = null;
	}

	public int getId() {
		return this.id;
	}

	public Response setId(int id) {
		this.id = id;
		setIdIsSet(true);
		return this;
	}

	public void unsetId() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield,
				__ID_ISSET_ID);
	}

	/**
	 * Returns true if field id is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetId() {
		return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
	}

	public void setIdIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID,
				value);
	}

	public boolean isOk() {
		return this.ok;
	}

	public Response setOk(boolean ok) {
		this.ok = ok;
		setOkIsSet(true);
		return this;
	}

	public void unsetOk() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield,
				__OK_ISSET_ID);
	}

	/**
	 * Returns true if field ok is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetOk() {
		return EncodingUtils.testBit(__isset_bitfield, __OK_ISSET_ID);
	}

	public void setOkIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __OK_ISSET_ID,
				value);
	}

	public String getDesc() {
		return this.desc;
	}

	public Response setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public void unsetDesc() {
		this.desc = null;
	}

	/**
	 * Returns true if field desc is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetDesc() {
		return this.desc != null;
	}

	public void setDescIsSet(boolean value) {
		if (!value) {
			this.desc = null;
		}
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case ID:
			if (value == null) {
				unsetId();
			} else {
				setId((Integer) value);
			}
			break;

		case OK:
			if (value == null) {
				unsetOk();
			} else {
				setOk((Boolean) value);
			}
			break;

		case DESC:
			if (value == null) {
				unsetDesc();
			} else {
				setDesc((String) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case ID:
			return Integer.valueOf(getId());

		case OK:
			return Boolean.valueOf(isOk());

		case DESC:
			return getDesc();

		}
		throw new IllegalStateException();
	}

	/**
	 * Returns true if field corresponding to fieldID is set (has been assigned
	 * a value) and false otherwise
	 */
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case ID:
			return isSetId();
		case OK:
			return isSetOk();
		case DESC:
			return isSetDesc();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof Response)
			return this.equals((Response) that);
		return false;
	}

	public boolean equals(Response that) {
		if (that == null)
			return false;

		boolean this_present_id = true;
		boolean that_present_id = true;
		if (this_present_id || that_present_id) {
			if (!(this_present_id && that_present_id))
				return false;
			if (this.id != that.id)
				return false;
		}

		boolean this_present_ok = true;
		boolean that_present_ok = true;
		if (this_present_ok || that_present_ok) {
			if (!(this_present_ok && that_present_ok))
				return false;
			if (this.ok != that.ok)
				return false;
		}

		boolean this_present_desc = true && this.isSetDesc();
		boolean that_present_desc = true && that.isSetDesc();
		if (this_present_desc || that_present_desc) {
			if (!(this_present_desc && that_present_desc))
				return false;
			if (!this.desc.equals(that.desc))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_id = true;
		list.add(present_id);
		if (present_id)
			list.add(id);

		boolean present_ok = true;
		list.add(present_ok);
		if (present_ok)
			list.add(ok);

		boolean present_desc = true && (isSetDesc());
		list.add(present_desc);
		if (present_desc)
			list.add(desc);

		return list.hashCode();
	}

	@Override
	public int compareTo(Response other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetId()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id,
					other.id);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetOk()).compareTo(other.isSetOk());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetOk()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ok,
					other.ok);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetDesc())
				.compareTo(other.isSetDesc());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetDesc()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.desc,
					other.desc);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		return 0;
	}

	public _Fields fieldForId(int fieldId) {
		return _Fields.findByThriftId(fieldId);
	}

	public void read(org.apache.thrift.protocol.TProtocol iprot)
			throws org.apache.thrift.TException {
		schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot)
			throws org.apache.thrift.TException {
		schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Response(");
		boolean first = true;

		sb.append("id:");
		sb.append(this.id);
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("ok:");
		sb.append(this.ok);
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("desc:");
		if (this.desc == null) {
			sb.append("null");
		} else {
			sb.append(this.desc);
		}
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		// check for sub-struct validity
	}

	private void writeObject(java.io.ObjectOutputStream out)
			throws java.io.IOException {
		try {
			write(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(out)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private void readObject(java.io.ObjectInputStream in)
			throws java.io.IOException, ClassNotFoundException {
		try {
			// it doesn't seem like you should have to do this, but java
			// serialization is wacky, and doesn't call the default constructor.
			__isset_bitfield = 0;
			read(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(in)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private static class ResponseStandardSchemeFactory
			implements SchemeFactory {
		public ResponseStandardScheme getScheme() {
			return new ResponseStandardScheme();
		}
	}

	private static class ResponseStandardScheme
			extends StandardScheme<Response> {

		public void read(org.apache.thrift.protocol.TProtocol iprot,
				Response struct) throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
				case 1: // ID
					if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
						struct.id = iprot.readI32();
						struct.setIdIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot,
								schemeField.type);
					}
					break;
				case 2: // OK
					if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
						struct.ok = iprot.readBool();
						struct.setOkIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot,
								schemeField.type);
					}
					break;
				case 3: // DESC
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.desc = iprot.readString();
						struct.setDescIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot,
								schemeField.type);
					}
					break;
				default:
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot,
							schemeField.type);
				}
				iprot.readFieldEnd();
			}
			iprot.readStructEnd();

			// check for required fields of primitive type, which can't be
			// checked in the validate method
			struct.validate();
		}

		public void write(org.apache.thrift.protocol.TProtocol oprot,
				Response struct) throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			oprot.writeFieldBegin(ID_FIELD_DESC);
			oprot.writeI32(struct.id);
			oprot.writeFieldEnd();
			oprot.writeFieldBegin(OK_FIELD_DESC);
			oprot.writeBool(struct.ok);
			oprot.writeFieldEnd();
			if (struct.desc != null) {
				oprot.writeFieldBegin(DESC_FIELD_DESC);
				oprot.writeString(struct.desc);
				oprot.writeFieldEnd();
			}
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class ResponseTupleSchemeFactory implements SchemeFactory {
		public ResponseTupleScheme getScheme() {
			return new ResponseTupleScheme();
		}
	}

	private static class ResponseTupleScheme extends TupleScheme<Response> {

		@Override
		public void write(org.apache.thrift.protocol.TProtocol prot,
				Response struct) throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			BitSet optionals = new BitSet();
			if (struct.isSetId()) {
				optionals.set(0);
			}
			if (struct.isSetOk()) {
				optionals.set(1);
			}
			if (struct.isSetDesc()) {
				optionals.set(2);
			}
			oprot.writeBitSet(optionals, 3);
			if (struct.isSetId()) {
				oprot.writeI32(struct.id);
			}
			if (struct.isSetOk()) {
				oprot.writeBool(struct.ok);
			}
			if (struct.isSetDesc()) {
				oprot.writeString(struct.desc);
			}
		}

		@Override
		public void read(org.apache.thrift.protocol.TProtocol prot,
				Response struct) throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			BitSet incoming = iprot.readBitSet(3);
			if (incoming.get(0)) {
				struct.id = iprot.readI32();
				struct.setIdIsSet(true);
			}
			if (incoming.get(1)) {
				struct.ok = iprot.readBool();
				struct.setOkIsSet(true);
			}
			if (incoming.get(2)) {
				struct.desc = iprot.readString();
				struct.setDescIsSet(true);
			}
		}
	}

}