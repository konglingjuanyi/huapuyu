object FrmEdit: TFrmEdit
  Left = 0
  Top = 0
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = #20108#25163#25151#31649#29702' -> '#32534#36753
  ClientHeight = 696
  ClientWidth = 1076
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnShow = FormShow
  PixelsPerInch = 96
  TextHeight = 13
  object gbDetailInfo: TGroupBox
    Left = 562
    Top = 8
    Width = 506
    Height = 624
    Caption = #35814#32454#36164#26009#65288#36873#22635#65289
    TabOrder = 1
    object Label21: TLabel
      Left = 16
      Top = 123
      Width = 60
      Height = 13
      Caption = #35013#20462#31243#24230#65306
    end
    object Label23: TLabel
      Left = 16
      Top = 153
      Width = 60
      Height = 13
      Caption = #24314#31569#24180#20195#65306
    end
    object Label24: TLabel
      Left = 16
      Top = 63
      Width = 60
      Height = 13
      Caption = #20303#23429#31867#21035#65306
    end
    object Label25: TLabel
      Left = 16
      Top = 183
      Width = 60
      Height = 13
      Caption = #24314#31569#31867#21035#65306
    end
    object Label26: TLabel
      Left = 16
      Top = 93
      Width = 60
      Height = 13
      Caption = #25151#23627#32467#26500#65306
    end
    object Label31: TLabel
      Left = 16
      Top = 33
      Width = 60
      Height = 13
      Caption = #29289#19994#31867#22411#65306
    end
    object Label32: TLabel
      Left = 338
      Top = 153
      Width = 12
      Height = 13
      Caption = #24180
    end
    object Label33: TLabel
      Left = 16
      Top = 213
      Width = 60
      Height = 13
      Caption = #30475#25151#26102#38388#65306
    end
    object Label34: TLabel
      Left = 16
      Top = 243
      Width = 60
      Height = 13
      Caption = #37197#22871#35774#26045#65306
    end
    object Label35: TLabel
      Left = 16
      Top = 313
      Width = 60
      Height = 13
      Caption = #20132#36890#29366#20917#65306
    end
    object Label36: TLabel
      Left = 16
      Top = 383
      Width = 60
      Height = 13
      Caption = #21608#36793#37197#22871#65306
    end
    object Label37: TLabel
      Left = 16
      Top = 453
      Width = 60
      Height = 13
      Caption = #25151#23627#25551#36848#65306
    end
    object Label38: TLabel
      Left = 16
      Top = 523
      Width = 60
      Height = 13
      Caption = #25151#28304#29305#33394#65306
    end
    object Label39: TLabel
      Left = 338
      Top = 313
      Width = 138
      Height = 13
      Caption = #20363#22914#65306#22320#38081'13'#21495#32447#12289'367'#12290
    end
    object Label40: TLabel
      Left = 338
      Top = 383
      Width = 156
      Height = 13
      Caption = #20363#22914#65306#24037#21830#38134#34892#12289#20154#27665#21307#38498#12290
    end
    object cmbDecoration: TComboBox
      Left = 82
      Top = 120
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 3
    end
    object cmbConstructYear: TComboBox
      Left = 82
      Top = 150
      Width = 248
      Height = 21
      Style = csDropDownList
      Sorted = True
      TabOrder = 4
    end
    object cmbPropType: TComboBox
      Left = 82
      Top = 60
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 1
      Items.Strings = (
        'adfafasdfasfasd'
        'afdasd'
        'asf'
        'asfsafas')
    end
    object cmbConstructType: TComboBox
      Left = 82
      Top = 180
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 5
    end
    object cmbPropStruct: TComboBox
      Left = 82
      Top = 90
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 2
    end
    object cmbPropMgtType: TComboBox
      Left = 82
      Top = 30
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 0
    end
    object cmbVisitTime: TComboBox
      Left = 82
      Top = 210
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 6
    end
    object clbFacility: TCheckListBox
      Left = 82
      Top = 240
      Width = 248
      Height = 61
      ItemHeight = 13
      TabOrder = 7
    end
    object clbFeature: TCheckListBox
      Left = 82
      Top = 520
      Width = 248
      Height = 60
      ItemHeight = 13
      TabOrder = 11
    end
    object memTransport: TMemo
      Left = 82
      Top = 310
      Width = 247
      Height = 61
      MaxLength = 500
      TabOrder = 8
    end
    object memEnvironment: TMemo
      Left = 82
      Top = 380
      Width = 247
      Height = 61
      MaxLength = 500
      TabOrder = 9
    end
    object memRemark: TMemo
      Left = 82
      Top = 450
      Width = 247
      Height = 61
      MaxLength = 500
      TabOrder = 10
    end
  end
  object gbEssentialInfo: TGroupBox
    Left = 8
    Top = 8
    Width = 548
    Height = 624
    Caption = #22522#26412#36164#26009#65288#24517#22635#65289
    TabOrder = 0
    object Label27: TLabel
      Left = 76
      Top = 33
      Width = 60
      Height = 13
      Caption = #27004#30424#21517#31216#65306
    end
    object Label1: TLabel
      Left = 16
      Top = 63
      Width = 120
      Height = 13
      Caption = #30465#12289#33258#27835#21306#12289#30452#36758#24066#65306
    end
    object Label2: TLabel
      Left = 100
      Top = 93
      Width = 36
      Height = 13
      Caption = #22478#24066#65306
    end
    object Label3: TLabel
      Left = 64
      Top = 123
      Width = 72
      Height = 13
      Caption = #21306#12289#21439#12289#24066#65306
    end
    object Label4: TLabel
      Left = 76
      Top = 153
      Width = 60
      Height = 13
      Caption = #35814#32454#22320#22336#65306
    end
    object Label5: TLabel
      Left = 100
      Top = 183
      Width = 36
      Height = 13
      Caption = #21806#20215#65306
    end
    object Label7: TLabel
      Left = 100
      Top = 273
      Width = 36
      Height = 13
      Caption = #21333#20215#65306
    end
    object Label8: TLabel
      Left = 398
      Top = 183
      Width = 136
      Height = 13
      Caption = #19975#20803'/'#22871#65292#26368#22810#20004#20301#23567#25968#12290
    end
    object Label10: TLabel
      Left = 398
      Top = 273
      Width = 52
      Height = 13
      Caption = #20803'/'#24179#26041#31859
    end
    object Label11: TLabel
      Left = 76
      Top = 213
      Width = 60
      Height = 13
      Caption = #24314#31569#38754#31215#65306
    end
    object Label12: TLabel
      Left = 76
      Top = 243
      Width = 60
      Height = 13
      Caption = #20351#29992#38754#31215#65306
    end
    object Label13: TLabel
      Left = 100
      Top = 303
      Width = 36
      Height = 13
      Caption = #25143#22411#65306
    end
    object Label14: TLabel
      Left = 166
      Top = 303
      Width = 12
      Height = 13
      Caption = #23460
    end
    object Label15: TLabel
      Left = 216
      Top = 303
      Width = 12
      Height = 13
      Caption = #21381
    end
    object Label16: TLabel
      Left = 266
      Top = 303
      Width = 12
      Height = 13
      Caption = #21416
    end
    object Label17: TLabel
      Left = 316
      Top = 303
      Width = 12
      Height = 13
      Caption = #21355
    end
    object Label18: TLabel
      Left = 100
      Top = 333
      Width = 36
      Height = 13
      Caption = #26397#21521#65306
    end
    object Label19: TLabel
      Left = 88
      Top = 363
      Width = 48
      Height = 13
      Caption = #24635#27004#23618#65306
    end
    object Label20: TLabel
      Left = 76
      Top = 393
      Width = 60
      Height = 13
      Caption = #25152#22312#27004#23618#65306
    end
    object Label22: TLabel
      Left = 76
      Top = 423
      Width = 60
      Height = 13
      Caption = #20135#26435#24615#36136#65306
    end
    object Label28: TLabel
      Left = 366
      Top = 303
      Width = 24
      Height = 13
      Caption = #38451#21488
    end
    object Label29: TLabel
      Left = 398
      Top = 213
      Width = 132
      Height = 13
      Caption = #24179#26041#31859#65292#26368#22810#20004#20301#23567#25968#12290
    end
    object Label30: TLabel
      Left = 398
      Top = 243
      Width = 132
      Height = 13
      Caption = #24179#26041#31859#65292#26368#22810#20004#20301#23567#25968#12290
    end
    object Label6: TLabel
      Left = 398
      Top = 363
      Width = 12
      Height = 13
      Caption = #23618
    end
    object Label9: TLabel
      Left = 398
      Top = 393
      Width = 12
      Height = 13
      Caption = #23618
    end
    object edtName: TEdit
      Left = 142
      Top = 30
      Width = 248
      Height = 21
      MaxLength = 50
      TabOrder = 0
      OnExit = edtNameExit
    end
    object cmbProvince: TComboBox
      Left = 142
      Top = 60
      Width = 248
      Height = 21
      Style = csDropDownList
      Enabled = False
      TabOrder = 1
      OnChange = cmbProvinceChange
    end
    object cmbCity: TComboBox
      Left = 142
      Top = 90
      Width = 248
      Height = 21
      Style = csDropDownList
      Enabled = False
      TabOrder = 2
      OnChange = cmbCityChange
    end
    object cmbDistrict: TComboBox
      Left = 142
      Top = 120
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 3
    end
    object edtPrice: TEdit
      Left = 142
      Top = 180
      Width = 248
      Height = 21
      MaxLength = 8
      TabOrder = 5
      OnExit = edtNameExit
    end
    object edtUnitPrice: TEdit
      Left = 142
      Top = 270
      Width = 248
      Height = 21
      Enabled = False
      TabOrder = 8
    end
    object edtBuildingArea: TEdit
      Left = 142
      Top = 210
      Width = 248
      Height = 21
      MaxLength = 8
      TabOrder = 6
      OnExit = edtNameExit
    end
    object edtAddress: TEdit
      Left = 142
      Top = 150
      Width = 248
      Height = 21
      MaxLength = 100
      TabOrder = 4
      OnExit = edtNameExit
    end
    object edtBedroomCount: TEdit
      Left = 142
      Top = 300
      Width = 18
      Height = 21
      MaxLength = 2
      TabOrder = 9
      OnExit = edtNameExit
    end
    object edtLivingRoomCount: TEdit
      Left = 192
      Top = 300
      Width = 18
      Height = 21
      MaxLength = 2
      TabOrder = 10
      OnExit = edtNameExit
    end
    object edtKitchenCount: TEdit
      Left = 242
      Top = 300
      Width = 18
      Height = 21
      MaxLength = 2
      TabOrder = 11
      OnExit = edtNameExit
    end
    object edtWashroomCount: TEdit
      Left = 292
      Top = 300
      Width = 18
      Height = 21
      MaxLength = 2
      TabOrder = 12
      OnExit = edtNameExit
    end
    object edtUsableArea: TEdit
      Left = 142
      Top = 240
      Width = 248
      Height = 21
      MaxLength = 8
      TabOrder = 7
      OnExit = edtNameExit
    end
    object cmbDirection: TComboBox
      Left = 142
      Top = 330
      Width = 248
      Height = 21
      Style = csDropDownList
      ItemIndex = 0
      TabOrder = 14
      Text = #21271
      Items.Strings = (
        #21271
        #19996
        #21335
        #35199)
    end
    object edtTotalFloor: TEdit
      Left = 142
      Top = 360
      Width = 248
      Height = 21
      MaxLength = 3
      TabOrder = 15
      OnExit = edtNameExit
    end
    object edtFloor: TEdit
      Left = 142
      Top = 390
      Width = 248
      Height = 21
      MaxLength = 3
      TabOrder = 16
      OnExit = edtNameExit
    end
    object cmbProperty: TComboBox
      Left = 142
      Top = 420
      Width = 248
      Height = 21
      Style = csDropDownList
      TabOrder = 17
    end
    object edtBalconyCount: TEdit
      Left = 342
      Top = 300
      Width = 18
      Height = 21
      MaxLength = 2
      TabOrder = 13
      OnExit = edtNameExit
    end
  end
  object btnNew: TButton
    Left = 768
    Top = 648
    Width = 75
    Height = 25
    Caption = #26032#24314
    TabOrder = 2
    OnClick = btnNewClick
  end
end
