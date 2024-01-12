### ✈ API 명세서
https://airy-hardhat-419.notion.site/b5149b1a89d0442395de92d136e7c448?v=4efcf271bb694ccd932fc12579258f40&pvs=4

### 📑 Architecture
![image](https://github.com/Team-Sopetit/Sopetit-server/assets/80771842/fc4c347d-03fd-4298-a097-3110d36c92ad)

### 📋 Model Diagram
![image](https://github.com/Team-Sopetit/Sopetit-server/assets/80771842/a260dcff-292b-4dd1-8acd-030a57a57a65)

### 📖 Directory
```
📁 Sopetit-server
├── .github
├── .idea
├── build
├── gen
├── gradel
├── scripts
├── src.main
│   ├──java.com.soptie.server
│       ├── auth
│       ├── common
│       ├── conversation
│       ├── doll
│       ├── member
│           ├── controller
│           ├── dto
│           ├── entity
│           ├── message
│           ├── repository
│           ├── service
│       ├── memberDoll
│       ├── memberRoutine
│       ├── routine
│       ├── test
├── src.test
│    ├──java.com.soptie.server
│       ├── auth
│       ├── base
│       ├── doll
│       ├── member
│       ├── memberRoutine
│           ├── controller
│           ├── fixture
│       ├── routine
│       ├── test
```

### ✉️ Commit Messge Rules

**서버** 들의 **Git Commit Message Rules**

- 반영사항을 바로 확인할 수 있도록 작은 기능 하나라도 구현되면 커밋을 권장합니다.
- 기능 구현이 완벽하지 않을 땐, 각자 브랜치에 커밋을 해주세요.

### 📌 Commit Convention

**[태그] 제목의 형태**

| 태그 이름 |                       설명                        |
| :-------: | :-----------------------------------------------: |
|   FEAT    |             새로운 기능을 추가할 경우             |
|    FIX    |                 버그를 고친 경우                  |
|   CHORE   |                    짜잘한 수정                    |
|   DOCS    |                     문서 수정                     |
|   INIT    |                     초기 설정                     |
|   TEST    |      테스트 코드, 리펙토링 테스트 코드 추가       |
|  RENAME   | 파일 혹은 폴더명을 수정하거나 옮기는 작업인 경우  |
|   STYLE   | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| REFACTOR  |                   코드 리팩토링                   |

### **커밋 타입**

- `[태그] 설명` 형식으로 커밋 메시지를 작성합니다.
- 태그는 영어를 쓰고 대문자로 작성합니다.

예시 >

```
  [FEAT] 검색 api 추가
```

### **💻 Github mangement**

**소프티** 들의 WorkFlow : **Gitflow Workflow**

- Develop, Feature, Hotfix 브랜치

- 개발(develop): 기능들의 통합 브랜치

- 기능 단위 개발(feature): 기능 단위 브랜치

- 버그 수정 및 갑작스런 수정(hotfix): 수정 사항 발생 시 브랜치

- 개발 브랜치 아래 기능별 브랜치를 만들어 작성합니다.

### ✍🏻 Code Convention

[에어비앤비 코드 컨벤션](https://github.com/airbnb/javascript)

### 📍 Gitflow 규칙

- Develop에 직접적인 commit, push는 금지합니다.
- 커밋 메세지는 다른 사람들이 봐도 이해할 수 있게 써주세요.
- 작업 이전에 issue 작성 후 pullrequest 와 issue를 연동해 주세요.
- 풀리퀘스트를 통해 코드 리뷰를 전원이 코드리뷰를 진행합니다.
- 기능 개발 시 개발 브랜치에서 feature/기능 으로 브랜치를 파서 관리합니다.
- feature 자세한 기능 한 가지를 담당하며, 기능 개발이 완료되면 각자의 브랜치로 Pull Request를 보냅니다.
- 각자가 기간 동안 맡은 역할을 전부 수행하면, 각자 브랜치에서 develop브랜치로 Pull Request를 보냅니다.  
  **develop 브랜치로의 Pull Request는 상대방의 코드리뷰 후에 merge할 수 있습니다.**

### ❗️ branch naming convention

- develop
- feature/issue_number-도메인-http Method-api
- fix/issue_number-도메인-http Method-api
- release/version_number
- hotfix/issue_number - Short Description

예시 >

```
  feature/#3-user-post-api
```

### 📋 Code Review Convention

- P1: 꼭 반영해주세요 (Request changes)
- P2: 적극적으로 고려해주세요 (Request changes)
- P3: 웬만하면 반영해 주세요 (Comment)
- P4: 반영해도 좋고 넘어가도 좋습니다 (Approve)
- P5: 그냥 사소한 의견입니다 (Approve)

### 👩‍👧‍👧 Our Team

|        **🍀 [최승빈](https://github.com/csb9427)**  |    **🍀 [남궁찬](https://github.com/Chan531)**                 |**🍀 [김소현](https://github.com/thguss)**                 |
  |:-----------------------------------:|:-----------------------------------:|:-----------------------------------:|
|   Server Developer  |    Server Developer     |   Server Developer |
| ![image](https://github.com/Team-Sopetit/Sopetit-server/assets/80771842/4eaa9aaa-b834-4883-91c8-cb5dd3005c5d) |   ![image](https://github.com/Team-Sopetit/Sopetit-server/assets/80771842/3e82a81c-1710-4199-8c5c-c920fdb8229b) |   ![image](https://github.com/Team-Sopetit/Sopetit-server/assets/80771842/ca9420e7-744d-4725-a9d9-36f79669fd04) |
|        프로젝트 세팅<br />    |       프로젝트 셋팅<br/>      |   프로젝트 세팅<br />    |  
