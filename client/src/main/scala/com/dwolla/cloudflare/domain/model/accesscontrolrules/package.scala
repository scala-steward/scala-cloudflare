package com.dwolla.cloudflare.domain.model


import com.dwolla.circe._
import io.circe._
import io.circe.generic.semiauto
import shapeless.tag.@@

package object accesscontrolrules {

  type AccessControlRuleId = String @@ AccessControlRuleIdTag
  type AccessControlRuleMode = String @@ AccessControlRuleModeTag
  type AccessControlRuleConfigurationTarget = String @@ AccessControlRuleConfigurationTargetTag
  type AccessControlRuleConfigurationValue = String @@ AccessControlRuleConfigurationValueTag
  type AccessControlRuleScopeId = String @@ AccessControlRuleScopeIdTag
  type AccessControlRuleScopeName = String @@ AccessControlRuleScopeNameTag
  type AccessControlRuleScopeType = String @@ AccessControlRuleScopeTypeTag

  private[cloudflare] val tagAccessControlRuleId: String => AccessControlRuleId = shapeless.tag[AccessControlRuleIdTag][String]
  private[cloudflare] val tagAccessControlRuleMode: String => AccessControlRuleMode = shapeless.tag[AccessControlRuleModeTag][String]
  private[cloudflare] val tagAccessControlRuleConfigurationTarget: String => AccessControlRuleConfigurationTarget = shapeless.tag[AccessControlRuleConfigurationTargetTag][String]
  private[cloudflare] val tagAccessControlRuleConfigurationValue: String => AccessControlRuleConfigurationValue = shapeless.tag[AccessControlRuleConfigurationValueTag][String]
  private[cloudflare] val tagAccessControlRuleScopeId: String => AccessControlRuleScopeId = shapeless.tag[AccessControlRuleScopeIdTag][String]
  private[cloudflare] val tagAccessControlRuleScopeName: String => AccessControlRuleScopeName = shapeless.tag[AccessControlRuleScopeNameTag][String]
  private[cloudflare] val tagAccessControlRuleScopeType: String => AccessControlRuleScopeType = shapeless.tag[AccessControlRuleScopeTypeTag][String]
}

package accesscontrolrules {

  import java.time.Instant

  trait AccessControlRuleIdTag
  trait AccessControlRuleModeTag
  trait AccessControlRuleConfigurationTargetTag
  trait AccessControlRuleConfigurationValueTag
  trait AccessControlRuleScopeIdTag
  trait AccessControlRuleScopeNameTag
  trait AccessControlRuleScopeTypeTag

  case class AccessControlRule(id: Option[AccessControlRuleId] = None,
                                  notes: Option[String] = None,
                                  allowed_modes: List[String] = List(),
                                  mode: AccessControlRuleMode,
                                  configuration: AccessControlRuleConfiguration,
                                  created_on: Option[Instant] = None,
                                  modified_on: Option[Instant] = None,
                                  scope: Option[AccessControlRuleScope] = None)

  object AccessControlRule extends DurationAsSecondsCodec with NullAsEmptyListCodec  {
    implicit val accessControlRuleCodec: Codec[AccessControlRule] = semiauto.deriveCodec
  }

  case class AccessControlRuleConfiguration(target: AccessControlRuleConfigurationTarget,
                                            value: AccessControlRuleConfigurationValue)

  object AccessControlRuleConfiguration {
    implicit val accessControlRuleConfigurationCodec: Codec[AccessControlRuleConfiguration] = semiauto.deriveCodec
  }

  case class AccessControlRuleScope(id: AccessControlRuleScopeId,
                                    name: Option[AccessControlRuleScopeName],
                                    `type`: AccessControlRuleScopeType)

  object AccessControlRuleScope {
    implicit val accessControlRuleScopeCodec: Codec[AccessControlRuleScope] = semiauto.deriveCodec
  }

}
