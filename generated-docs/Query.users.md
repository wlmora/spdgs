# Query.users: [User]
                 
## Arguments
| Name        | Description | Required |  Type  |
|:------------|:------------|:--------:|:------:|
| emailFilter |             | Optional | String |
            
## Example
```graphql
{
  users(emailFilter: "randomString") {
    id
    firstname
    lastname
    email
    password
    isEnable
  }
}

```