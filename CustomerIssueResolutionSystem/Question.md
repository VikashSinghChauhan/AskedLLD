An e-commerce website like Amazon/Flipkart etc serve millions of orders every day.
Many times customers face issues with the order they did and reach out to
customer support of these websites to raise those issues.
Customer support is nothing but a group of agents with skills for solving a set of issues and they solve customer's issues.

Customer Issues can be classified into multiple types like order delayed, Payment Related, items being shipped to the wrong address etc.
Design a customer issue resolution system which is used to assign customer's issues to agents and can track agent's work history.

Low Level Design (object oriented design) implementation requirements :
- - Your code will be tested in a MULTI-THREADED environment, so use thread safe data structures and handle synchronization properly.

- Your solution should implement the following functions. Feel free to use the representation for objects you deem fit for the problem and the provided use cases.

Method : void init(List[String] issueTypes, Helper03 helper)
When this method is called, then initialize and reset all of your instance variables and system state.
- System will have at max 20 different issueTypes, treat all strings in issue type as case-insensitive. e.g. payment failed, order delayed, need to change address etc
- helper will be used for printing logs.

Method : String createIssue(String issueId, String orderId, int issueType, String description)
- This method will be used to create a new customer issue.
- There can be upto 10^5 unique issues at most.
- each orderId and issueId will be unique and non-empty
- Each issue will have a issueType is index of strings from issueTypes array in init() method. It will always be valid.
- description : e.g. Money deducted from bank but payment failed

- returns below strings
- "issue created" : when issue is successfully created
- "issue already exists" : in case another issue with same issueId has already been created.
- "invalid issue type" : in case issueType is not found in issueTypes list passed in init() method.

Method : String addAgent(String agentId, List[Integer] expertise)
- At most there will be 1000 agents.
- agentId should be a unique, non-empty string, if duplicate then return "agent already exists"
- expertise is comma separated, 0-based index of issueTypes used in init() method e.g "2,7,8",
- they are the issues the agent is expert in,
- returns "success" or "agent already exists"

Method : String assignIssue(String issueId, int assignStrategy)
This method should assign an issue to an agent which has the skill to solve issue's issueType
- issueId is id of an issue which has already been created and which needs to be assigned to the agent.
- below are various types of strategies used for assigning issues to agents.
- assignStrategy= 0 : Assign an agent which has lowest number of total issues open. Used For efficient processing.
- assignStrategy= 1 : Assign an agent who has the most experience in resolving issue of type in issueId. For resolving issues which may require insight and more expertise.
- assignStrategy= 2 : Assign an agent who has the least number of open issues of issue type in [issueId]. Used to training agents on a particular issueType.
  Note :
- In all the above strategies if there are multiple such agents assign issue to any one of them.
- An agent can be assigned an issue only if they have an expertise with that issue.
- returns id of the agent or "issue doesn't exist", "issue already assigned", "agent with expertise doesn't exist"

Method : resolveIssue(String issueId, String resolution)
This method resolves the issue,
- issue is resolved by the agent who was assigned the issue with issueId
- issueId will refer to an existing issue. will always be valid.
- resolution can be e.g. money refunded to customer.

Method : List[String] getAgentHistory(String agentId)
This method should returns list of issueId's of issues resolved by agent or in other words list of issues assigned to agent with which are in resolved state now.
- return an empty list in case agent doesn't exists or no issue has been resolved yet by the agent