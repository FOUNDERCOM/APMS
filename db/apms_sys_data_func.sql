???prompt PL/SQL Developer import file
prompt Created on 2017年9月28日 by Jimmybly Lee
set feedback off
set define off
prompt Disabling triggers for APMS_SYS_FUNC...
alter table APMS_SYS_FUNC disable all triggers;
prompt Deleting APMS_SYS_FUNC...
delete from APMS_SYS_FUNC;
commit;
prompt Loading APMS_SYS_FUNC...
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10103000, -10100000, 'salary', '辅警工资', 'MENU', '菜单', 3, 'icon-credit-card', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10102040, -10102000, 'aux_dept', '本部辅警', 'FUNC', '本单位拥有的辅警', 4, 'icon-check', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10102030, -10102000, 'aux_log', '申请记录', 'FUNC', '在辅警上报注册审批过程中的工作记录', 3, 'icon-book-open', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10102020, -10102000, 'aux_approve', '辅警审核', 'FUNC', '审核科所队上报注册登记的辅警人员信息', 2, 'icon-eye', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10102010, -10102000, 'aux_apply', '辅警登记', 'FUNC', '科所队，登记辅警人员情况', 1, 'icon-user-follow', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10102000, -10100000, 'aux', '辅警管理', 'MENU', '菜单', 2, 'icon-users', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10101000, -10100000, 'home', '首页', 'FUNC', '通用工作台', 1, 'icon-home', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10100000, -10000000, 'apms', '辅警管理系统', 'GROUP', '分组', 1, 'icon-home', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10000000, null, 'root', '根菜单', 'ROOT', '无', 1, 'icon-home', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10103010, -10103000, 'salary_mgmt', '工资维护', 'FUNC', '维护本单位的辅警工资信息', 1, 'icon-note', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10103020, -10103000, 'salary_book', '花名册', 'FUNC', '本单位的辅警人员工资花名册', 2, 'icon-notebook', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10104000, -10100000, 'analysis', '分析决策', 'MENU', '菜单', 4, 'icon-rocket', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10104010, -10104000, 'analysis_capability', '战力分析', 'FUNC', '各分局、科所队团队配置情况及人员战斗力分析', 1, 'icon-like', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10104020, -10104000, 'analysis_check', '辅警抽查', 'FUNC', '政工抽查各级科所队辅警人员情况', 2, 'icon-eyeglasses', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10104030, -10104000, 'analysis_supervise', '检查分析', 'FUNC', '分析各级科所队相应辅警情况', 3, 'icon-speedometer', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10200000, -10000000, 'setting', '系统配置', 'GROUP', '分组', 2, 'icon-home', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10201000, -10200000, 'dept', '部门管理', 'MENU', '菜单', 1, 'icon-globe', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10201010, -10201000, 'dept_bureau', '分局管理', 'FUNC', '管理分局基本信息', 1, 'icon-puzzle', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10201020, -10201000, 'dept_station', '科所队', 'FUNC', '管理本单位的科所队信息', 2, 'icon-paper-clip', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10202000, -10200000, 'sys', '系统管理', 'MENU', '菜单', 2, 'icon-settings', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10202010, -10202000, 'sys_dict', '字典管理', 'FUNC', '管理系统字典标准信息', 1, 'icon-layers', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10202020, -10202000, 'sys_func', '功能管理', 'FUNC', '管理系统功能', 2, 'icon-flag', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10202030, -10202000, 'sys_user', '用户管理', 'FUNC', '管理本单位下辖直属单位用户', 3, 'icon-users', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10202040, -10202000, 'sys_auth', '权限管理', 'FUNC', '管理本单位下辖直属单位的用户权限', 4, 'icon-key', '1', '1');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10300000, -10000000, 'common', '通用', 'GROUP', '分组', 3, 'icon-home', '1', '0');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10301000, -10300000, 'about', '关于我们', 'FUNC', '关于我们', 1, 'icon-info', '1', '0');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10302000, -10300000, 'help', '系统帮助', 'FUNC', '系统使用说明', 2, 'icon-support', '1', '0');
insert into APMS_SYS_FUNC (func_id, func_parent_id, func_code, func_name, func_type, func_desc, func_seq, func_icon, func_is_built_in, is_enabled)
values (-10303000, -10300000, 'contact', '联系我们', 'FUNC', '各市局分局科所队的联系方式', 3, 'icon-call-end', '1', '0');
commit;
prompt 28 records loaded
prompt Enabling triggers for APMS_SYS_FUNC...
alter table APMS_SYS_FUNC enable all triggers;
set feedback on
set define on
prompt Done.
