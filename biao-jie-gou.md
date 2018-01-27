## 家庭表：family

| **字段** | **类型** | **说明** |
| :--- | :--- | :--- |
| id | int | pk ai |
| name | varchar\(100\) | 家庭名 |

## 用户表：user

| **字段** | **类型** | **说明** |
| :--- | :--- | :--- |
| id | int | pk ai |
| name | varchar\(100\) | 用户名 |
| password | varchar\(100\) | 密码 |
| family\_id | int | 所在家庭ID |

## 科目表：account

| **字段** | **类型** | **说明** |
| :--- | :--- | :--- |
| id | int | pk ai |
| type | varchar\(100\) | 类型：资产，负债，收入，支出 |
| name | varchar\(100\) | 名称 |

## 账本表：book

| **字段** | **类型** | **说明** |
| :--- | :--- | :--- |
| id | int | pk ai |
| user\_id | int | 用户ID |
| account\_id | int | 科目ID |
| account\_date | date | 记账日期 |
| summary | varchar\(100\) | 摘要 |
| amount | double | 金额 |





